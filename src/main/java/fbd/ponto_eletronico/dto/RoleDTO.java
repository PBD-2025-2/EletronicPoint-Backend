package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.Company;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    @JoinColumn(columnDefinition = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"id", "cnpj"})
    private Company company;
}
