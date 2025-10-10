package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pbd.ponto_eletronico.enums.TypeRoster;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RosterDTO (Long id, String name, TypeRoster type, String weeklyWorkload, List<DailySchedule> dailySchedules, DutySchedules dutySchedules){
}
