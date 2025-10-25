package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DailySchedule;
import pbd.ponto_eletronico.enums.TypeRoster;

import java.util.List;

public record RosterDiaryPutRequest(Long id, String name, int weeklyWorkload, List<DailySchedule> schedules) implements RosterPutRequest {
    @Override
    public TypeRoster type() {
        return TypeRoster.Plantão;
    }
}
