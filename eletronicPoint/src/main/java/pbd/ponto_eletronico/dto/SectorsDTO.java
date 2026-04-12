package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SectorsDTO(
        Long id,
        String name,
        String sigla,
        CompanyEmbeddedDTO company
) {}
