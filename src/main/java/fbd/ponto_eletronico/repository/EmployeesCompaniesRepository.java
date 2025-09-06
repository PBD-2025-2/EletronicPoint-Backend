package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.EmployeesCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesCompaniesRepository extends JpaRepository<EmployeesCompanies, Long> {
    List<EmployeesCompanies> findByEmployee(Employee employee);
    List<EmployeesCompanies> findByCompany(Company company);
}