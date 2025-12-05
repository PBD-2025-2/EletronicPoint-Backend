package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedules;
import pbd.ponto_eletronico.enums.RosterType;

public record RosterDutyPostRequest(String name, int weeklyWorkload, DutySchedules schedules) implements RosterPostRequest {
    @Override
    public RosterType type() {
        return RosterType.Plantão;
    }
}
