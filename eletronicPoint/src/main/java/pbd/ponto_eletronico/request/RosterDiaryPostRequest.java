package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DailySchedule;
import pbd.ponto_eletronico.enums.RosterType;

import java.time.LocalTime;
import java.util.List;

public record RosterDiaryPostRequest(
        String name,
        int weeklyWorkload,
        LocalTime dailyWorkloadLimit,
        List<DailySchedule> schedules
        ) implements RosterPostRequest{

    @Override
    public RosterType type() {
        return RosterType.Diaria;
    }
}
