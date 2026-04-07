package pbd.ponto_eletronico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record DutySchedules(String startTime, Integer workDuration, Integer timeOff) {}
