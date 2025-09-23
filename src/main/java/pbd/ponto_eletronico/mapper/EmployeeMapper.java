package pbd.ponto_eletronico.mapper;

import pbd.ponto_eletronico.dto.EmployeeDTO;
import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.request.EmployeePostRequest;
import pbd.ponto_eletronico.request.EmployeePutRequest;
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