package pbd.ponto_eletronico.dto;

import pbd.ponto_eletronico.enums.TypeRoster;

public record DutySchedulesDTO(Long id, String name, TypeRoster type, String weeklyWorkload, DutySchedules dutySchedules) {}