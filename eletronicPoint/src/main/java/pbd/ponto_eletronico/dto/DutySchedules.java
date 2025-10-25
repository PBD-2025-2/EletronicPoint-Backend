package pbd.ponto_eletronico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DutySchedules {
    private String startTime;
    private Integer workDuration;
    private Integer timeOff;
}
