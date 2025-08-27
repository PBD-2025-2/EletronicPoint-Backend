package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDTO> employeeDtos(List<Employee> employees);

}
