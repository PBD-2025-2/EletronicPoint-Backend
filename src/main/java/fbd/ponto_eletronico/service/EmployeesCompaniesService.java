package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.EmployeesCompanies;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.mapper.EmployeeMapper;
import fbd.ponto_eletronico.mapper.EmployeesCompaniesMapper;
import fbd.ponto_eletronico.repository.EmployeesCompaniesRepository;
import fbd.ponto_eletronico.request.EmployeesCompaniesPostRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesCompaniesService {
    private final EmployeesCompaniesRepository employeesCompaniesRepository;
    private final EmployeesCompaniesMapper employeesCompaniesMapper;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public List<EmployeesCompaniesDTO> findAll(){
        List<EmployeesCompanies> employeesCompanies = employeesCompaniesRepository.findAll();
        return employeesCompaniesMapper.toEmployeesCompaniesDtos(employeesCompanies);
    }

    public EmployeesCompaniesDTO findById(Long id) {
        Optional<EmployeesCompanies> employeesCompaniesData = employeesCompaniesRepository.findById(id);
        return employeesCompaniesData.map(employeesCompaniesMapper :: toEmployeesCompaniesDto)
                .orElseThrow(() -> new BadRequestException("Id not Found"));
    }

    public List<EmployeesCompaniesDTO> findByEmployee(String cpf) {
        List<Employee> employeesData = employeeMapper.toEmployees(employeeService.findByCpf(cpf));
        List<EmployeesCompanies> employeesCompaniesData = employeesCompaniesRepository.findByEmployee(employeesData.getFirst());
        return employeesCompaniesMapper.toEmployeesCompaniesDtos(employeesCompaniesData);
    }

    public List<EmployeesCompaniesDTO> findByCompany(String cnpj) {
        List<Company> companiesData = companyMapper.toCompanies(companyService.findByCnpj(cnpj));
        List<EmployeesCompanies> employeesCompaniesData = employeesCompaniesRepository.findByCompany(companiesData.getFirst());
        return employeesCompaniesMapper.toEmployeesCompaniesDtos(employeesCompaniesData);
    }

    @Transactional
    public EmployeesCompanies save(EmployeesCompaniesPostRequest employeesCompaniesPostRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(employeesCompaniesPostRequest.companyId()));
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeesCompaniesPostRequest.employeeId()));
        EmployeesCompanies employeesCompaniesData = employeesCompaniesMapper.toEmployeesCompanies(employeesCompaniesPostRequest);
        employeesCompaniesData.setCompany(companyData);
        employeesCompaniesData.setEmployee(employeeData);
        return employeesCompaniesRepository.save(employeesCompaniesData);
    }

    public void delete(Long id) {
        EmployeesCompanies employeesCompaniesData = employeesCompaniesMapper.toEmployeesCompanies(findById(id));
        employeesCompaniesRepository.delete(employeesCompaniesData);
    }
}
