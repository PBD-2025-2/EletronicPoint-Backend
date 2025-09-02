package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.entity.EletronicPoints;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface EletronicPointsMapper {

    List<EletronicPointsDTO> eletronicPointsDto(List<EletronicPoints> eletronicPoints);

}

