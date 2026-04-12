package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pbd.ponto_eletronico.enums.RosterType;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RosterDTO (
        Long id,
        String name,
        RosterType type,
        int weeklyWorkload,
        LocalTime dailyWorkloadLimit,
        String workPattern,
        Object schedules){

    public record Summary(Long id, String name){}
}
