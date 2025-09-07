package fbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record EmployeesRolesPostRequest (@NotEmpty(message = "Status Not Found") Boolean status,
                                         @NotEmpty(message = "Work_Regime Not Found") Integer work_regime,
                                         @NotEmpty(message = "Id Not Found")Long employeeId,
                                         @NotEmpty(message = "Id Not Found")Long roleId,
                                         @NotEmpty(message = "Id Not Found")Long companyId){}
