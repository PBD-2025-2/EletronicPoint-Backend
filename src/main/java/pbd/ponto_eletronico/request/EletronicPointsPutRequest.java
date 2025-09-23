package pbd.ponto_eletronico.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record EletronicPointsPutRequest(
        LocalDate startDate,
        LocalTime register_1,
        LocalTime register_2,
        LocalTime register_3,
        LocalTime register_4,
        LocalDate endDate,
        Integer status) {}
