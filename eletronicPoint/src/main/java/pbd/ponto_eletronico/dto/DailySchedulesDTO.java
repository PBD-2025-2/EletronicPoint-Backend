package pbd.ponto_eletronico.dto;

import pbd.ponto_eletronico.enums.TypeRoster;

import java.util.List;

public record DailySchedulesDTO(Long id, String name, TypeRoster type, String weeklyWorkload, List<DailySchedule> schedules) {
}
