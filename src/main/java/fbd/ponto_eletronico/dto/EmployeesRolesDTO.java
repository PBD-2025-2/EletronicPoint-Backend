package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record EmployeesRolesDTO (
        Long id,
        Boolean status,
        Integer workRegime,
        EmployeeEmbeddedDTO employee,
        @JsonIgnoreProperties({"id"}) RoleDTO role){}
