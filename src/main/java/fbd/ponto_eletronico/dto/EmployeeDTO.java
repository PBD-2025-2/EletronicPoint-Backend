package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;

public record EmployeeDTO (@Id Long id, String name, @JsonIgnore String cpf){}
