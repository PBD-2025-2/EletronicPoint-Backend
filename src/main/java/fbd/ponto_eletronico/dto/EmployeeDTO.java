package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private CompanyEmbeddedDTO company;
}
