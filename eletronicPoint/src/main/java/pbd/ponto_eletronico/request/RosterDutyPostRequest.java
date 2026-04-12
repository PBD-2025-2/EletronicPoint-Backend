package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedules;
import pbd.ponto_eletronico.enums.RosterType;

import java.time.LocalTime;

public record RosterDutyPostRequest(
        String name,
        int weeklyWorkload,
        LocalTime dailyWorkloadLimit,
        DutySchedules schedules
        ) implements RosterPostRequest {
    @Override
    public RosterType type() {
        return RosterType.Plantão;
    }
}
