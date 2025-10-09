package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.DailySchedulesDTO;
import pbd.ponto_eletronico.dto.DutySchedulesDTO;
import pbd.ponto_eletronico.entity.Roster;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, DutySchedulesMapper.class})
public interface RosterMapper {
    Roster rosterDtoToRoster(DailySchedulesDTO rosterDTO);
    DailySchedulesDTO rosterToRosterDto(Roster roster);
    List<DailySchedulesDTO> listRosterToListRosterDto(List<Roster> rosters);
    List<Roster> listRosterDtoTOListRoster(List<DailySchedulesDTO> rosterDTOS);
    DutySchedulesDTO rostertoDutySchedulesDTO(Roster roster);
}
