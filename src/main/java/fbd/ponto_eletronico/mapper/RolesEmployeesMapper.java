package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.RolesEmployeesDTO;
import fbd.ponto_eletronico.entity.RolesEmployees;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesEmployeesMapper {
    List<RolesEmployeesDTO> rolesEmployeesDtos(List<RolesEmployees> rolesEmployees);
}
