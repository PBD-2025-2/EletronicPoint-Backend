package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DutySchedules;
import pbd.ponto_eletronico.enums.TypeRoster;

public record RosterDutyPostRequest(String name, int weeklyWorkload, DutySchedules schedules) implements RosterPostRequest {
    @Override
    public TypeRoster type() {
        return TypeRoster.Plantão;
    }
}
