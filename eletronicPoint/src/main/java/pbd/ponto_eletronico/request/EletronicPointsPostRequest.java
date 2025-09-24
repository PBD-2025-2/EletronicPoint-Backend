package pbd.ponto_eletronico.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record EletronicPointsPostRequest(
        Long employeesRolesId,
        LocalDate startDate,
        LocalTime register_1,
        Integer status) {}
