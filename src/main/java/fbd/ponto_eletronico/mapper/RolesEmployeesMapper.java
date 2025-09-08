package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesEmployeesMapper {
    List<EmployeesRolesDTO> employeesRolesDtos(List<EmployeesRoles> employeesRoles);

    List<EmployeesRoles> toEmployeesRoles(List<EmployeesRolesDTO> employeesRolesDtos);

    List<Role> toRoles(List<EmployeesRoles> employeesRoles);

    EmployeesRolesDTO employeeRolesDto(EmployeesRoles employeesRoles);

    EmployeesRoles toEmployeesRoles(EmployeesRolesDTO employeesRolesDTO);

    EmployeesRoles toEmployeesRoles(EmployeesRolesPostRequest employeesRolesPostRequest);

}
