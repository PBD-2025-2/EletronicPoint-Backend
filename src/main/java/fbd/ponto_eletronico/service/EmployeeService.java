package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.mapper.EmployeeMapper;
import fbd.ponto_eletronico.repository.EmployeeRepository;
import fbd.ponto_eletronico.request.EmployeePostRequest;
import fbd.ponto_eletronico.request.EmployeePutRequest;
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
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public List<EmployeeDTO> findAll(){
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toEmployeeDtos(employees);
    }

    public EmployeeDTO findById(Long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);
        return employeeData.map(employeeMapper ::toEmployeeDto)
                .orElseThrow(() -> new BadRequestException("Id not Found"));
    }

    public List<EmployeeDTO> findByName(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        return employeeMapper.toEmployeeDtos(employees);
    }

    public List<EmployeeDTO> findByCpf(String cpf) {
        List<Employee> employees = employeeRepository.findByCpf(cpf);
        return employeeMapper.toEmployeeDtos(employees);
    }

    @Transactional
    public Employee save(EmployeePostRequest employeePostRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(employeePostRequest.companyId()));
        Employee employeeData = employeeMapper.toEmployee(employeePostRequest);
        employeeData.setCompany(companyData);
        return employeeRepository.save(employeeData);
    }

    public Employee replace(Long id, EmployeePutRequest employeePutRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(employeePutRequest.companyId()));
        Employee employeeData = employeeMapper.toEmployee(findById(id));
        Employee employeeReplace = employeeMapper.toEmployee(employeePutRequest);
        employeeReplace.setId(employeeData.getId());
        employeeReplace.setCompany(companyData);
        return employeeRepository.save(employeeReplace);
    }

    public void delete(Long id){
        Employee employeeData = employeeMapper.toEmployee(findById(id));
        employeeRepository.delete(employeeData);
    }
}
