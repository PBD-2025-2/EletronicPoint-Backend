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


    public EmployeesRoles getEmployeesRoles() {
        return employeesRoles;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Time getRegister_1() {
        return register_1;
    }

    public Time getRegister_2() {
        return register_2;
    }

    public Time getRegister_3() {
        return register_3;
    }

    public Time getRegister_4() {
        return register_4;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setEmployeesRoles(EmployeesRoles employeesRoles) {
        this.employeesRoles = employeesRoles;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRegister_1(Time register_1) {
        this.register_1 = register_1;
    }

    public void setRegister_2(Time register_2) {
        this.register_2 = register_2;
    }

    public void setRegister_3(Time register_3) {
        this.register_3 = register_3;
    }

    public void setRegister_4(Time register_4) {
        this.register_4 = register_4;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
