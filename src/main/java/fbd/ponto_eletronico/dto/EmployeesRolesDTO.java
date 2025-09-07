package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.Role;
import lombok.Data;

@Data
public class EmployeesRolesDTO {
    private Long id;
    private Boolean status;
    private Integer work_regime;
    @JsonIgnoreProperties({"company", "cpf"})
    private Employee employee;
    @JsonIgnoreProperties({"id", "company"})
    private Role role;
    @JsonIgnoreProperties({"id", "cnpj"})
    private Company company;
}
