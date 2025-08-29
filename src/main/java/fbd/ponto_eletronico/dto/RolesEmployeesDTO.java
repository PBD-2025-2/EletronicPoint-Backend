package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.Role;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class RolesEmployeesDTO {
    private Long id;
    private Boolean status;
    private Integer work_regime;

    @JoinColumn(columnDefinition = "employee_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"id", "cpf", "company"})
    private Employee employee;

    @JoinColumn(columnDefinition = "role_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"id"})
    private Role role;
}
