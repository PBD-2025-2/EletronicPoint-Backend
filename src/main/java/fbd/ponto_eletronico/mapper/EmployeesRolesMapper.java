package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import fbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeesRolesMapper {
    List<EmployeesRolesDTO> toEmployeesRolesDtos(List<EmployeesRoles> employeesRoles);

    List<EmployeesRoles> toEmployeesRoles(List<EmployeesRolesDTO> employeesRolesDtos);

    EmployeesRolesDTO toEmployeeRolesDto(EmployeesRoles employeesRoles);

    EmployeesRoles toEmployeesRolesPut(EmployeesRolesPutRequest employeesRolesPutRequest);

    EmployeesRoles toEmployeesRoles(EmployeesRolesDTO employeesRolesDTO);

    EmployeesRoles toEmployeesRoles(EmployeesRolesPostRequest employeesRolesPostRequest);

}
