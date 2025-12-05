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

    public RosterDTO save(RosterPostRequest rosterPostRequest){
        if(rosterPostRequest instanceof  RosterDiaryPostRequest diaryPostRequest){
            if (!requestIsValid(diaryPostRequest)) {
                throw new BadRequestException("Request Invalid");}
        }
        Roster roster = new Roster();
        roster.setName(rosterPostRequest.name());
        roster.setWeeklyWorkload(rosterPostRequest.weeklyWorkload());
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
        setRosterSchedulesAndType(rosterData, ((RosterPostRequest) rosterPutRequest));
        return rosterMapper.rosterToRosterDTO(rosterRepository.save(rosterData));
    }

    private void setRosterSchedulesAndType(Roster roster, RosterPostRequest rosterPostRequest){
        roster.setType(rosterPostRequest.type());
        if(rosterPostRequest.type() == RosterType.Diaria){
            if(rosterPostRequest instanceof  RosterDiaryPostRequest diaryPostRequest){
            roster.setSchedules(diaryPostRequest.schedules());}
            else if(rosterPostRequest instanceof RosterDiaryPutRequest diaryPutRequest){
                roster.setSchedules(diaryPutRequest.schedules());
            }
        }else if(rosterPostRequest.type() == RosterType.Plantão){
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
}
