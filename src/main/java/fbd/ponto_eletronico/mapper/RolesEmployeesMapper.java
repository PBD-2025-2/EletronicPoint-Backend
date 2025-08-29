package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesEmployeesMapper {
    List<EmployeesRolesDTO> employeesRolesDtos(List<EmployeesRoles> employeesRoles);
}
