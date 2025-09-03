package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.serialization.EmployeesRolesSerialize;
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
    @JsonSerialize(using = EmployeesRolesSerialize.class)
    private EmployeesRoles employeesRoles;
}
