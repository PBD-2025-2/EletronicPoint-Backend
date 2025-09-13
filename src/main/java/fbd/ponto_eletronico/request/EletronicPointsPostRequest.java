package fbd.ponto_eletronico.request;

import fbd.ponto_eletronico.entity.EmployeesRoles;

import java.time.LocalDate;
import java.time.LocalTime;

public record EletronicPointsPostRequest(
        Long employeesRolesId,
        LocalDate startDate,
        LocalTime register_1,
        Integer status) {}
