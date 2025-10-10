package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.DailySchedulesDTO;
import pbd.ponto_eletronico.dto.DutySchedulesDTO;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.entity.Roster;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, DutySchedulesMapper.class})
public interface RosterMapper {
    Roster rosterDtoToRoster(DailySchedulesDTO rosterDTO);
    DailySchedulesDTO rosterToRosterDto(Roster roster);
    DutySchedulesDTO rostertoDutySchedulesDTO(Roster roster);
    List<RosterDTO> listRostertoRosterDTO (List<Roster> rosters);
}
