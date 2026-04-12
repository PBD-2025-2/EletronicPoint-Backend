package pbd.ponto_eletronico.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbd.ponto_eletronico.dto.*;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.enums.RosterType;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.RosterMapper;
import pbd.ponto_eletronico.repository.RosterRepository;
import pbd.ponto_eletronico.request.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RosterService {

    private final RosterRepository rosterRepository;
    private final RosterMapper rosterMapper;

    public List<RosterDTO.Summary> findAll() throws JsonProcessingException {
        List<Roster> rosters = rosterRepository.findAll();
        return rosterMapper.rostersToRosterDTOSummary(rosters);
    }

    public RosterDTO findById(Long id) {
        Optional<Roster> rosterData = rosterRepository.findById(id);
        return rosterMapper.rosterToRosterDTO(rosterData.
                orElseThrow(() -> new BadRequestException("No rosters found with this ID!")));
    }

    public List<RosterDTO> findByName(String name) {
        List<Roster> rosterData = rosterRepository.findByName(name);
        if(rosterData.isEmpty()){
            throw new BadRequestException("No rosters found with this name!");
        }
        return rosterMapper.rostersToRosterDTOs(rosterData);
    }

    public RosterDTO save(RosterPostRequest rosterPostRequest){

        Roster roster = new Roster();
        roster.setName(rosterPostRequest.name());
        roster.setWeeklyWorkload(rosterPostRequest.weeklyWorkload());
        roster.setDailyWorkloadLimit(rosterPostRequest.dailyWorkloadLimit());

        if(rosterPostRequest instanceof  RosterDiaryPostRequest diaryPostRequest){
            if (!requestIsValid(diaryPostRequest)) {
                throw new BadRequestException("Request Invalid");}
            roster.setWorkPattern(workPatternDiary(diaryPostRequest.schedules()));
        }
        if(rosterPostRequest instanceof RosterDutyPostRequest dutyPostRequest){
            roster.setWorkPattern(workPatternDuty(dutyPostRequest.schedules()));
        }
        setRosterSchedulesAndType(roster, rosterPostRequest);
        Roster rosterData = rosterRepository.save(roster);
        return rosterMapper.rosterToRosterDTO(rosterData);
    }

    public RosterDTO replace(Long id, RosterPutRequest rosterPutRequest){
        if(rosterPutRequest instanceof  RosterDiaryPutRequest diaryPutRequest){
            if (!requestIsValid(diaryPutRequest)) {
                throw new BadRequestException("Request Invalid");}
        }
        Roster rosterData = rosterMapper.rosterDTOToRoster(findById(id));
        rosterData.setId(id);
        rosterData.setName(rosterPutRequest.name());
        rosterData.setWeeklyWorkload(rosterPutRequest.weeklyWorkload());
        rosterData.setDailyWorkloadLimit(rosterPutRequest.dailyWorkloadLimit());
        setRosterSchedulesAndType(rosterData, ((RosterPostRequest) rosterPutRequest));
        return rosterMapper.rosterToRosterDTO(rosterRepository.save(rosterData));
    }

    private void setRosterSchedulesAndType(Roster roster, RosterPostRequest rosterPostRequest){
        roster.setType(rosterPostRequest.type());

        if(rosterPostRequest.type() == RosterType.Diaria){
            if(rosterPostRequest instanceof  RosterDiaryPostRequest diaryPostRequest){
                roster.setSchedules(diaryPostRequest.schedules());
            }
            else if(rosterPostRequest instanceof RosterDiaryPutRequest diaryPutRequest){
                roster.setSchedules(diaryPutRequest.schedules());
            }

        } else if(rosterPostRequest.type() == RosterType.Plantão){
            if(rosterPostRequest instanceof RosterDutyPostRequest dutyPostRequest){
                roster.setSchedules(dutyPostRequest.schedules());
            }else if(rosterPostRequest instanceof  RosterDutyPutRequest dutyPutRequest)
            roster.setSchedules(dutyPutRequest.schedules());

        }else{
            throw new IllegalArgumentException("Type invalid");
        }
    }

    private boolean requestIsValid(RosterDiaryPostRequest rosterPostRequest) {
        if (rosterPostRequest.schedules().size() > 7) {
            throw new BadRequestException("The times are exceeding the limit");
        }

        if (!verifyDuplicateDayOfWeek(rosterPostRequest.schedules())) {
            throw new BadRequestException("Existing repetead days.");
        }

        return true;
    }
    private boolean requestIsValid(RosterDiaryPutRequest rosterDiaryPutRequest) {
        if (rosterDiaryPutRequest.schedules().size() > 7) {
            throw new BadRequestException("The times are exceeding the limit");
        }

        if (!verifyDuplicateDayOfWeek(rosterDiaryPutRequest.schedules())) {
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

    private String workPatternDiary(List<DailySchedule> schedules){
        int size = schedules.size();

        return switch (size) {
            case 1 -> "1x6";
            case 2 -> "2x5";
            case 3 -> "3x4";
            case 4 -> "4x3";
            case 5 -> "5x2";
            case 6 -> "6x1";
            case 7 -> "7x0";
            default -> "0x0";
        };

    }
    private String workPatternDuty(DutySchedules dutySchedules){
        return dutySchedules.workDuration() + "x" + dutySchedules.timeOff();
    }
}
