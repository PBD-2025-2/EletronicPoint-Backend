package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.enums.OriginType;

import java.time.LocalDate;
import java.time.LocalTime;

public record EletronicPointsPostRequest(
        Long employeesRolesId,
        LocalDate startDate,
        LocalTime register_1,
        OriginType origin,
        Integer status) {}
