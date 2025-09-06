package fbd.ponto_eletronico.dto;

import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;

public record EmployeesCompaniesDTO(Long id, EmployeeEmbeddedDTO employee, CompanyEmbeddedDTO company) {}