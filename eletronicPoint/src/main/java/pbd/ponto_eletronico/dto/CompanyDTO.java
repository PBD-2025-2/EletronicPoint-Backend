package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record CompanyDTO (
        Long id,
        String name,
        String cnpj,
        String email,
        Object address,
        String phoneNumber) {

    public record Summary(Long id, String name) {
    }

    public record Details(Long id,
                          String name,
                          String email,
                          Object address,
                          String phoneNumber){}

}