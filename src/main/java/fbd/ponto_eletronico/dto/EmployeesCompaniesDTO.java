package fbd.ponto_eletronico.dto;

public record EmployeesCompaniesDTO(Long id, EmployeeEmbeddedDTO employeeEmbeddedDTO, CompanyEmbeddedDTO companyEmbeddedDTO) {}