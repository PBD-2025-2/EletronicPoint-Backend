package pbd.ponto_eletronico.dto;

public record EmployeesCompaniesDTO(
        Long id,
        EmployeeDTO.Name employee,
        CompanyEmbeddedDTO company) {}