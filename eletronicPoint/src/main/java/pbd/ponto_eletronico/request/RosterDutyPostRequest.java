package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedulesDTO;
import pbd.ponto_eletronico.dto.ScheduleDTO;
import pbd.ponto_eletronico.enums.TypeRoster;

import java.util.List;

public record RosterDutyPostRequest(String name, String weeklyWorkload, TypeRoster type, DutySchedulesDTO schedules) {}
