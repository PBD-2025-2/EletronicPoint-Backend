package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record EmployeesCompaniesPutRequest(Long companyId,
                                           Long employeeId) {}
