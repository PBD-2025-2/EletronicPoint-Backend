package pbd.ponto_eletronico.request;

public record SectorsPostRequest(
        String name,
        String sigla,
        Long companyId
) {}
