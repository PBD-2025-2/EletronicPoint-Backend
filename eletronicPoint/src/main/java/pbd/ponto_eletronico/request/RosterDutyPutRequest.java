package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedules;
import pbd.ponto_eletronico.enums.RosterType;

public record RosterDutyPutRequest(String name, int weeklyWorkload, DutySchedules schedules) implements RosterPutRequest {
    @Override
    public RosterType type() {
        return RosterType.Plantão;
    }
}
