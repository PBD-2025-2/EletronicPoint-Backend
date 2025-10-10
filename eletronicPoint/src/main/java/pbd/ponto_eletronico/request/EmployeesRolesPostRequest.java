package pbd.ponto_eletronico.request;

public record EmployeesRolesPostRequest (Boolean status,
                                         Long idRoster,
                                         Long employeeId,
                                         Long roleId){}
