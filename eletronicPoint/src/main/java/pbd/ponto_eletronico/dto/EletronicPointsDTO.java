package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import pbd.ponto_eletronico.enums.OriginType;
import pbd.ponto_eletronico.serialize.StatusSerialize;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EletronicPointsDTO(
        @Id
        Long id,
        LocalDate startDate,
        LocalTime register_1,
        LocalTime register_2,
        LocalTime register_3,
        LocalTime register_4,
        LocalDate endDate,

        @JsonSerialize(using = StatusSerialize.class)
        Integer status,
        OriginType origin,
        @JsonIgnoreProperties({"id"}) EmployeesRolesDTO employeesRoles)  {}