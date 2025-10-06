package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.request.RosterPostRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RosterMapper {

    Roster rosterDtoToRoster(RosterDTO rosterDTO);

    RosterDTO rosterToRosterDto(Roster roster);

    List<RosterDTO> listRosterToListRosterDto(List<Roster> rosters);


}
