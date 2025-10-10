package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedules;

public record RosterDutyPostRequest(String name, int weeklyWorkload, DutySchedules dutySchedules) {}
