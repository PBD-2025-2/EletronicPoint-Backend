package fbd.ponto_eletronico.request;

public record EmployeesRolesPutRequest(
        Boolean status,
        Integer workRegime,
        Long employeeId,
        Long roleId) {}
