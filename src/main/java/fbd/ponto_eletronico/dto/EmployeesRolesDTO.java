package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import serialize.WorkRegimeSerialize;

public record EmployeesRolesDTO (
        Long id,
        Boolean status,
        @JsonSerialize(using = WorkRegimeSerialize.class)
        Integer workRegime,
        EmployeeEmbeddedDTO employee,
        @JsonIgnoreProperties({"id"}) RoleDTO role){}
