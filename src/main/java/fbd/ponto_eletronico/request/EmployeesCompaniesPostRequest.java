package fbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record EmployeesCompaniesPostRequest(Long companyId,
                                            Long employeeId) {}
