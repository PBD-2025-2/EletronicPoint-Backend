package pbd.ponto_eletronico.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pbd.ponto_eletronico.dto.*;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.enums.TypeRoster;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.DutySchedulesMapper;
import pbd.ponto_eletronico.mapper.RosterMapper;
import pbd.ponto_eletronico.mapper.ScheduleMapper;
import pbd.ponto_eletronico.repository.RosterRepository;
import pbd.ponto_eletronico.request.RosterDiaryPostRequest;
import pbd.ponto_eletronico.request.RosterDutyPostRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RosterService {

    private final RosterRepository rosterRepository;
    private final RosterMapper rosterMapper;
    private final ScheduleMapper scheduleMapper;
    private final DutySchedulesMapper dutySchedulesMapper;

    public List<RosterDTO> findAll() throws JsonProcessingException {
        List<Roster> rosters = rosterRepository.findAll();
        return rosterMapper.listRostertoRosterDTO(rosters);
    }

    public RosterDTO findById(Long id) {
        Optional<Roster> rosterData = rosterRepository.findById(id);
        return rosterMapper.rosterToRosterDTO(rosterData.
                orElseThrow(() -> new BadRequestException("Id Not Found")));
    }

    public RosterDTO findByName(String name) {
        Roster rosterData = rosterRepository.findByName(name);
        return rosterMapper.rosterToRosterDTO(rosterData);
    }

    @Transactional
    public DailySchedulesDTO registerDailySchedule(RosterDiaryPostRequest rosterPostRequest) {
        if (!requestIsValid(rosterPostRequest)) {
            throw new BadRequestException("Request Invalid");
        }

        Roster roster = new Roster();
        roster.setName(rosterPostRequest.name());
        roster.setWeeklyWorkload(rosterPostRequest.weeklyWorkload());
        roster.setType(TypeRoster.Diaria);

        List<DailySchedule> dailySchedules = rosterPostRequest.dailySchedules().stream().toList();
        roster.setDailySchedules(dailySchedules);


        return rosterMapper.rosterToDailySchedulesDTO(rosterRepository.save(roster));
    }

    public DutySchedulesDTO registerDutySchedule(RosterDutyPostRequest rosterDutyPostRequest){
        Roster roster = new Roster();
        roster.setName(rosterDutyPostRequest.name());
        roster.setWeeklyWorkload(rosterDutyPostRequest.weeklyWorkload());
        roster.setType(TypeRoster.Plantão);

        roster.setDutySchedules(rosterDutyPostRequest.dutySchedules());

        return rosterMapper.rostertoDutySchedulesDTO(rosterRepository.save(roster));
    }

    private boolean requestIsValid(RosterDiaryPostRequest rosterPostRequest) {
        if (rosterPostRequest.dailySchedules().size() > 7) {
            throw new BadRequestException("The times are exceeding the limit");
        }

        if (!verifyDuplicateDayOfWeek(rosterPostRequest.dailySchedules())) {
            throw new BadRequestException("Existing repetead days.");
        }

        return true;
    }

    private boolean verifyDuplicateDayOfWeek(List<DailySchedule> schedules) {
        Set<String> seen = new HashSet<>();
        boolean hasDuplicates = schedules.stream()
                .map(DailySchedule::day)
                .anyMatch(day -> !seen.add(day));

        return !hasDuplicates;
    }
}
