package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.EletronicPointsArchiveDTO;
import pbd.ponto_eletronico.entity.EletronicPointsArchive;
import pbd.ponto_eletronico.request.EletronicPointsArchivePutRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EletronicPointsArchiveMapper {
    EletronicPointsArchive eletronicPointsArchiveDTOToEletronicPointsArchive(EletronicPointsArchiveDTO eletronicPointsArchiveDTO);
    EletronicPointsArchiveDTO eletronicPointsArchiveToEletronicPointsArchiveDTO(EletronicPointsArchive eletronicPointsArchive);
    EletronicPointsArchivePutRequest eletronicPointsArchiveToEletronicPointsArchivePutRequest(EletronicPointsArchive eletronicPointsArchive);
    List<EletronicPointsArchiveDTO> listEletronicPointsArchiveToListEletronicPointsArchiveDTO(List<EletronicPointsArchive> eletronicPointsArchives);
}
