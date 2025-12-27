package pbd.ponto_eletronico.dto;

public record EmployeesRolesDTO (
        Long id,
        Boolean status,
        EmployeeDTO employee,
        RosterEmbeddedDTO roster,
        RoleDTO role){}
