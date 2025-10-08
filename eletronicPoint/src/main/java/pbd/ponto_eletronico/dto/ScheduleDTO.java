package pbd.ponto_eletronico.dto;

import java.util.List;

public record ScheduleDTO(String day, List<String> schedules) {
}
