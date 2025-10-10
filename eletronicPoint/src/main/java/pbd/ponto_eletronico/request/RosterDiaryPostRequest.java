package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DailySchedule;

import java.util.List;

public record RosterDiaryPostRequest(String name, int weeklyWorkload, List<DailySchedule> dailySchedules) {}
