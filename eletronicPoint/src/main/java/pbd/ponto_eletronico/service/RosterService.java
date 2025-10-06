package pbd.ponto_eletronico.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pbd.ponto_eletronico.dto.DailyScaleDTO;
import pbd.ponto_eletronico.dto.DutyRosterDTO;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.enums.TypeRoster;
import pbd.ponto_eletronico.mapper.RosterMapper;
import pbd.ponto_eletronico.repository.RosterRepository;
import pbd.ponto_eletronico.request.RosterPostRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RosterService {


    private final RosterRepository rosterRepository;
    private final ObjectMapper objectMapper;
    private final RosterMapper rosterMapper;

    public List<RosterDTO> findAll(){
        List<Roster> rosters= rosterRepository.findAll();
        for (Roster roster : rosters) {
            if (roster == null) {
                throw new RuntimeException("Error, no schedule found");
            }
            roster.setSchedules(formatSchedule(roster.getSchedules()));
        }
        return rosterMapper.listRosterToListRosterDto(rosterRepository.findAll());
    }

    @Transactional
    public RosterDTO save(RosterPostRequest rosterPostRequest) throws JsonProcessingException {
        Roster roster = new Roster();
        roster.setType(rosterPostRequest.roster());
        roster.setName(rosterPostRequest.name());
        roster.setWeeklyWorkload(rosterPostRequest.weeklyWorkload());
        roster.setSchedules(convertListToStringJson(rosterPostRequest.dailyScaleDTOS(), rosterPostRequest.dutyRosterDTO(), rosterPostRequest.roster()));
        return rosterMapper.rosterToRosterDto(rosterRepository.save(roster));
    }


    public String convertListToStringJson(List<DailyScaleDTO> dailyScaleDTO, DutyRosterDTO dutyRosterDTO, TypeRoster typeRoster) throws JsonProcessingException {

        if(typeRoster.getRoster() != null && typeRoster.getRoster().equals( TypeRoster.Diaria.getRoster())){
            return objectMapper.writeValueAsString(dailyScaleDTO);
        }
        if (typeRoster.getRoster() != null && typeRoster.getRoster().equals(TypeRoster.Plantão.getRoster())){
            return objectMapper.writeValueAsString(dutyRosterDTO);
        }
        throw new RuntimeException("conversion error");
    }

    public String formatSchedule(String schedule){
        if(schedule == null){
            throw new RuntimeException("Error, no schedule found");
        }
        try{
            JsonElement elementSchedule = JsonParser.parseString(schedule);
            if(!elementSchedule.isJsonArray()){
                throw new RuntimeException("The format for schedule is not JSON Array");
            }
            JsonArray jsonArray = elementSchedule.getAsJsonArray();

            StringBuilder formattedOutput = new StringBuilder();

            for(int i = 0; i < jsonArray.size(); i ++){
                JsonObject scheduleDay = jsonArray.get(i).getAsJsonObject();

                String day = scheduleDay.get("day").getAsString();
                JsonArray periods = scheduleDay.get("period").getAsJsonArray();
                String periodsClean = StreamSupport.stream(periods.spliterator(), false)
                        .map(JsonElement::getAsString)
                        .collect(Collectors.joining(", "));

                formattedOutput.append("Day: ").append(day).append(", Period: ").append(periodsClean);

                if(i < jsonArray.size() -1){
                    formattedOutput.append("\n");
                }
            }
            return formattedOutput.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error processing json" + e);
        }

    }

}
