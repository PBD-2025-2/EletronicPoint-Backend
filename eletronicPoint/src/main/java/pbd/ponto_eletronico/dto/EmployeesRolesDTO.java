package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import pbd.ponto_eletronico.entity.Roster;
public record EmployeesRolesDTO (
        Long id,
        Boolean status,
//        @JsonSerialize(using = WorkRegimeSerialize.class)
        RosterEmbeddedDTO roster,
        EmployeeEmbeddedDTO employee,
        @JsonIgnoreProperties({"id"}) RoleDTO role){}
