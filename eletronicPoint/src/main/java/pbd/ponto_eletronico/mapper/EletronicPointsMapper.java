package pbd.ponto_eletronico.mapper;

import pbd.ponto_eletronico.dto.EletronicPointsDTO;
import pbd.ponto_eletronico.dto.EletronicPointsArchiveDTO;
import pbd.ponto_eletronico.entity.EletronicPoints;
import pbd.ponto_eletronico.entity.EletronicPointsArchive;
import pbd.ponto_eletronico.request.EletronicPointsPostRequest;
import pbd.ponto_eletronico.request.EletronicPointsPutRequest;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface EletronicPointsMapper {

    List<EletronicPointsDTO> toEletronicPointsDto(List<EletronicPoints> eletronicPoints);

    EletronicPointsDTO toEletronicPointsDto(EletronicPoints eletronicPoints);

    EletronicPoints toEletronicPoints(EletronicPointsDTO eletronicPointsDTO);

    EletronicPoints toEletronicPoints(EletronicPointsPostRequest eletronicPointsPostRequest);

    EletronicPoints eletronicPointsPutRequestToEletronicPoints(EletronicPointsPutRequest eletronicPointsPutRequest);

    EletronicPoints eletronicPointsArchiveToEletronicPoints(EletronicPointsArchive eletronicPointsArchive);

    EletronicPointsPutRequest eletronicPointsToEletronicPointsPutRequest(EletronicPoints eletronicPoints);

}

