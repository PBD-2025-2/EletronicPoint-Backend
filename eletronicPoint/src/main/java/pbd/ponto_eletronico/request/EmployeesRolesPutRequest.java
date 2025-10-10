package pbd.ponto_eletronico.request;

public record EmployeesRolesPutRequest(
        Boolean status,
        Long idRoster,
        Long employeeId,
        Long roleId) {}
