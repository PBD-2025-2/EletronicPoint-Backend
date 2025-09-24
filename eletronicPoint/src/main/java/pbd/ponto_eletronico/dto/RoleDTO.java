package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record RoleDTO (
        Long id,
        String name,
        @JsonIgnoreProperties({"id"}) CompanyDTO company){}
