package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.DailyScaleDTO;
import pbd.ponto_eletronico.dto.DutyRosterDTO;
import pbd.ponto_eletronico.enums.TypeRoster;
import java.util.List;

public record RosterPostRequest(String name, String weeklyWorkload, TypeRoster roster, List<DailyScaleDTO> dailyScaleDTOS, DutyRosterDTO dutyRosterDTO) {}
