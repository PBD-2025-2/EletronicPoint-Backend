package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.serialization.EmployeesRolesSerialize;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

public record EletronicPointsDTO(
        LocalDate startDate,
        Time register_1,
        Time register_2,
        Time register_3,
        Time register_4,
        LocalDate endDate,
        Integer status,
        @JsonSerialize(using = EmployeesRolesSerialize.class)
        EmployeesRoles employeesRoles)  {}