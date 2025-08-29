package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.Company;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    @JsonIgnoreProperties({"id", "cnpj"})
    private Company company;
}
