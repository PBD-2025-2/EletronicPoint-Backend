package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class EletronicPointsDTO {

    private LocalDate startDate;
    private Time register_1;
    private Time register_2;
    private Time register_3;
    private Time register_4;
    private LocalDate endDate;
    private Integer status;
    @JsonIgnoreProperties(value = {"id"})
    private EmployeesRoles employeesRoles;

}
