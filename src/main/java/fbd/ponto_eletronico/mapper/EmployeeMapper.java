package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.request.EmployeePostRequest;
import fbd.ponto_eletronico.request.EmployeePutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDTO employeeDTO);

    Employee toEmployee(EmployeePostRequest employeePostRequest);

    Employee toEmployee(EmployeePutRequest employeePutRequest);

    List<Employee> toEmployees(List<EmployeeDTO> employeeDTOS);

    EmployeeDTO toEmployeeDto(Employee employee);

    List<EmployeeDTO> toEmployeeDtos(List<Employee> employees);
}