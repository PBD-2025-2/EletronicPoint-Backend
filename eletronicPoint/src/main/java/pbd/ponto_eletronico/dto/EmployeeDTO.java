package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import pbd.ponto_eletronico.enums.GenderType;

public record EmployeeDTO (
        Long id,
        String name,
        @JsonIgnore String cpf,
        String email,
        String phoneNumber,
        GenderType gender,
        Object address){

    public record Id(Long id) {}
    public record Name(String name) {}
}

