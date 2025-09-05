package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.EmployeesCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesCompaniesRepository extends JpaRepository<EmployeesCompanies, Long> {
}