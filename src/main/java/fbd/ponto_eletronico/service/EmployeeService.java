package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.mapper.EmployeeMapper;
import fbd.ponto_eletronico.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private static final Logger log = LogManager.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> listAll(){
        List<Employee> employees = employeeRepository.findAll();
        log.info("Lista de Employees", employees);
        return employeeMapper.employeeDtos(employees);
    }

}
