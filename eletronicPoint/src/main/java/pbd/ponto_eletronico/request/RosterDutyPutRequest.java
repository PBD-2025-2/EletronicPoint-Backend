package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedules;
import pbd.ponto_eletronico.enums.TypeRoster;

public record RosterDutyPutRequest(String name, int weeklyWorkload, DutySchedules schedules) implements RosterPutRequest {
    @Override
    public TypeRoster type() {
        return TypeRoster.Plantão;
    }
}
