package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EletronicPoints;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import fbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface EletronicPointsMapper {

    List<EletronicPointsDTO> toEletronicPointsDto(List<EletronicPoints> eletronicPoints);

    List<EletronicPoints> toEletronicPoints(List<EletronicPointsDTO> eletronicPointsDTOS);

    EletronicPointsDTO toEletronicPointsDto(EletronicPoints eletronicPoints);

    EletronicPoints toEletronicPoints(EletronicPointsDTO eletronicPointsDTO);

//    EletronicPoints toEmployeesRoles(EmployeesRolesPostRequest employeesRolesPostRequest);

//    EletronicPoints toEmployeesRolesPut(EmployeesRolesPutRequest employeesRolesPutRequest);
}

