package fbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record EmployeesCompaniesPostRequest(@NotEmpty(message = "Id not found") Long companyId,
                                            @NotEmpty(message = "Id not found") Long employeeId) {}
