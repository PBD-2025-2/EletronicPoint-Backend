package pbd.ponto_eletronico.request;

public record SectorsPutRequest(
        String name,
        String sigla,
        Long companyId
) {}
