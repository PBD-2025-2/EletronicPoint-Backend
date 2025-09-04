package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CompanyDTO {

    @Id
    private Long id;
    private String name;
    @JsonIgnore
    private String cnpj;
}
