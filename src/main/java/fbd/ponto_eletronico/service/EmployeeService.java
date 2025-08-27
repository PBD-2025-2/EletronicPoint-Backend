package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.mapper.EmployeeMapper;
import fbd.ponto_eletronico.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> listAll(){
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.employeeDtos(employees);
    }

}
