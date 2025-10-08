package pbd.ponto_eletronico.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.DailyScaleDTO;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.request.RosterPostRequest;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, DutySchedulesMapper.class})
public interface RosterMapper {
    Roster rosterDtoToRoster(RosterDTO rosterDTO);
    RosterDTO rosterToRosterDto(Roster roster);
    List<RosterDTO> listRosterToListRosterDto(List<Roster> rosters);
    List<Roster> listRosterDtoTOListRoster(List<RosterDTO> rosterDTOS);
}
