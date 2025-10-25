package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.entity.Roster;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RosterMapper {
    Roster rosterDTOToRoster(RosterDTO rosterDTO);
    RosterDTO rosterToRosterDTO(Roster roster);
     List<RosterDTO> listRostertoRosterDTO (List<Roster> rosters);
}
