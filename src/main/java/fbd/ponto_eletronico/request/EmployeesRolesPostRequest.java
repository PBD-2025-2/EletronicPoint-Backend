package fbd.ponto_eletronico.request;

public record EmployeesRolesPostRequest (Boolean status,
                                         Integer work_regime,
                                         Long employeeId,
                                         Long roleId,
                                         Long companyId){}
