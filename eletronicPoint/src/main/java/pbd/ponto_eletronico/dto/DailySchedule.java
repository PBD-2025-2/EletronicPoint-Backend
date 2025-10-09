package pbd.ponto_eletronico.dto;

import java.util.List;

public record DailySchedule(String day, List<String> schedules) {}
