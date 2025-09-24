package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.entity.EmployeesCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesCompaniesRepository extends JpaRepository<EmployeesCompanies, Long> {
    List<EmployeesCompanies> findByEmployee(Employee employee);
    List<EmployeesCompanies> findByCompany(Company company);
}