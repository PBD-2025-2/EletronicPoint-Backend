package fbd.ponto_eletronico.request;

public record EmployeesRolesPostRequest (Boolean status,
                                         Integer workRegime,
                                         Long employeeId,
                                         Long roleId){}
