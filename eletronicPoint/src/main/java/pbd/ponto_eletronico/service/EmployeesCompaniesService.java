package pbd.ponto_eletronico.service;

import pbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.entity.EmployeesCompanies;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.CompanyMapper;
import pbd.ponto_eletronico.mapper.EmployeeMapper;
import pbd.ponto_eletronico.mapper.EmployeesCompaniesMapper;
import pbd.ponto_eletronico.repository.EmployeesCompaniesRepository;
import pbd.ponto_eletronico.request.EmployeesCompaniesPostRequest;
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
        if(employeesCompanies.isEmpty()){
            throw new BadRequestException("No employee companies found, please register!");
        }
        return employeesCompaniesMapper.toEmployeesCompaniesDtos(employeesCompanies);
    }

    public EmployeesCompaniesDTO findById(Long id) {
        Optional<EmployeesCompanies> employeesCompaniesData = employeesCompaniesRepository.findById(id);
        return employeesCompaniesMapper.toEmployeesCompaniesDto(employeesCompaniesData
                .orElseThrow(() -> new BadRequestException("No employees companies found with this ID!")));
    }

    public List<EmployeesCompaniesDTO> findByEmployee(String cpf) {
        Employee employeesData = employeeMapper.toEmployee(employeeService.findByCpf(cpf));
        if(employeesData == null){
            throw new BadRequestException("No employees companies found with this cpf!");
        }
        List<EmployeesCompanies> employeesCompaniesData = employeesCompaniesRepository.findByEmployee(employeesData);
        return employeesCompaniesMapper.toEmployeesCompaniesDtos(employeesCompaniesData);
    }

    public List<EmployeesCompaniesDTO> findByCompany(String cnpj) {
        List<Company> companiesData = companyMapper.toCompanies(companyService.findByCnpj(cnpj));
        if(companiesData.isEmpty()){
            throw new BadRequestException("No employees companies found with this CNPJ!");
        }
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
