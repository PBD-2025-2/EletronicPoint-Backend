package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import serialize.StatusSerialize;

import java.sql.Time;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EletronicPointsDTO(
        @Id
        Long id,
        LocalDate startDate,
        Time register_1,
        Time register_2,
        Time register_3,
        Time register_4,
        LocalDate endDate,

        @JsonSerialize(using = StatusSerialize.class)
        Integer status,

        @JsonIgnoreProperties({"id"}) EmployeesRolesDTO employeesRoles)  {}