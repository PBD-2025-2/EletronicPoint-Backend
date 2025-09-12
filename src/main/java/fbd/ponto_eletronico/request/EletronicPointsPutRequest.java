package fbd.ponto_eletronico.request;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EmployeesRoles;

import java.time.LocalDate;
import java.time.LocalTime;

public record EletronicPointsPutRequest(
        Long employeeRolesId,
        LocalDate startDate,
        LocalTime register_1,
        LocalTime register_2,
        LocalTime register_3,
        LocalTime register_4,
        LocalDate endDate,
        Integer status) {}
