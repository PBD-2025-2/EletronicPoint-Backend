package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.SectorsDTO;
import pbd.ponto_eletronico.entity.Sectors;
import pbd.ponto_eletronico.request.SectorsPostRequest;
import pbd.ponto_eletronico.request.SectorsPutRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorsMapper {
    Sectors sectorsDTOToSectors(SectorsDTO sectorsDTO);
    Sectors sectorsPostRequestToSectors(SectorsPostRequest sectorsPostRequest);
    Sectors sectorsPutRequestToSectors(SectorsPutRequest sectorsPutRequest);
    SectorsDTO sectorsToSectorsDTO(Sectors sectors);
    List<Sectors> listSectorsDTOToListSectors(List<SectorsDTO> sectorsDTOS);
    List<SectorsDTO> listSectorsToListSectorsDTO(List<Sectors> sectors);
}
