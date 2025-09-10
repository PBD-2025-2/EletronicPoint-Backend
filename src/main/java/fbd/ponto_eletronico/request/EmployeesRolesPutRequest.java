package fbd.ponto_eletronico.request;

public record EmployeesRolesPutRequest(
        Boolean status,
        Integer work_regime,
        Long employeeId,
        Long roleId,
        Long companyId) {}
