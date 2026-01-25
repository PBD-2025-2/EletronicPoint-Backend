package pbd.ponto_eletronico.service;

import pbd.ponto_eletronico.dto.EmployeeDTO;
import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.EmployeeMapper;
import pbd.ponto_eletronico.repository.EmployeeRepository;
import pbd.ponto_eletronico.request.EmployeePostRequest;
import pbd.ponto_eletronico.request.EmployeePutRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private static final Logger log = LogManager.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> findAll(){
        List<Employee> employeesData = employeeRepository.findAll();
        if(employeesData.isEmpty()){
            throw new BadRequestException("No employees found, please register!");
        }
        return employeeMapper.toEmployeeDtos(employeesData);
    }

    public EmployeeDTO findById(Long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);
        return employeeMapper.toEmployeeDto(employeeData
                .orElseThrow(() -> new BadRequestException("No employees found with this ID!")));
    }

    public List<EmployeeDTO> findByName(String name) {
        List<Employee> employeesData = employeeRepository.findByName(name);
        if(employeesData.isEmpty()){
            throw new BadRequestException("No employees found with this name!");
        }
        return employeeMapper.toEmployeeDtos(employeesData);
    }

    public List<EmployeeDTO> findByCpf(String cpf) {
        List<Employee> employees = employeeRepository.findByCpf(cpf);
        if(employees.isEmpty()){
            throw new BadRequestException("No employees found with this CPF!");
        }
        return employeeMapper.toEmployeeDtos(employees);
    }

    @Transactional
    public Employee save(EmployeePostRequest employeePostRequest) {
        Employee employeeData = employeeMapper.toEmployee(employeePostRequest);
        return employeeRepository.save(employeeData);
    }

    public Employee replace(Long id, EmployeePutRequest employeePutRequest) {
        Employee employeeData = employeeMapper.toEmployee(findById(id));
        Employee employeeReplace = employeeMapper.toEmployee(employeePutRequest);
        employeeReplace.setId(employeeData.getId());
        return employeeRepository.save(employeeReplace);
    }

    public void delete(Long id){
        Employee employeeData = employeeMapper.toEmployee(findById(id));
        employeeRepository.delete(employeeData);
    }
}
