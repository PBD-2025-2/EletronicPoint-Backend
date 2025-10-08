package pbd.ponto_eletronico.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.dto.ScheduleDTO;
import pbd.ponto_eletronico.entity.Schedule;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.enums.TypeRoster;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.DutySchedulesMapper;
import pbd.ponto_eletronico.mapper.RosterMapper;
import pbd.ponto_eletronico.mapper.ScheduleMapper;
import pbd.ponto_eletronico.repository.RosterRepository;
import pbd.ponto_eletronico.request.RosterPostRequest;

import java.util.HashSet;
import java.util.List;
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
        return rosterMapper.listRosterToListRosterDto(rosters);
    }

    @Transactional
    public RosterDTO save(RosterPostRequest rosterPostRequest) {
        if (!requestIsValid(rosterPostRequest)) {
            throw new BadRequestException("Request Invalid");
        }

        Roster roster = new Roster();
        roster.setName(rosterPostRequest.name());
        roster.setWeeklyWorkload(rosterPostRequest.weeklyWorkload());
        roster.setType(rosterPostRequest.type());

        if (rosterPostRequest.type() == TypeRoster.Diaria) {
            List<Schedule> schedules = rosterPostRequest.schedules().stream().map(scheduleMapper::scheduleDTOToSchedule).toList();
            roster.setSchedules(schedules);
        }

//        if (rosterPostRequest.type() == TypeRoster.Plantão) {
//            roster.setDutySchedules(dutySchedulesMapper.dutySchedulesDTOToDutySchedules(rosterPostRequest.dutySchedules()));
//        }

        return rosterMapper.rosterToRosterDto(rosterRepository.save(roster));
    }

    private boolean requestIsValid(RosterPostRequest rosterPostRequest) {
        if (rosterPostRequest.schedules().size() > 7) {
            throw new BadRequestException("The times are exceeding the limit");
        }

        if (!verifyDuplicateDayOfWeek(rosterPostRequest.schedules())) {
            throw new BadRequestException("Existing repetead days.");
        }

        return true;
    }

    private boolean verifyDuplicateDayOfWeek(List<ScheduleDTO> schedules) {
        Set<String> seen = new HashSet<>();
        boolean hasDuplicates = schedules.stream()
                .map(ScheduleDTO::day)
                .anyMatch(day -> !seen.add(day));

        return !hasDuplicates;
    }
}
