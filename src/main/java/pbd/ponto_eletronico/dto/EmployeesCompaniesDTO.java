package pbd.ponto_eletronico.dto;

public record EmployeesCompaniesDTO(
        Long id,
        EmployeeEmbeddedDTO employee,
        CompanyEmbeddedDTO company) {}