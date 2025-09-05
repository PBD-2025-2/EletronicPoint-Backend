package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;


public record CompanyDTO (@Id Long id, String name, @JsonIgnore String cnpj) {
}