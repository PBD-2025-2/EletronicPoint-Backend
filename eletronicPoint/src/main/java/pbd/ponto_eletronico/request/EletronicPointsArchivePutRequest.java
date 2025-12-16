package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.dto.EletronicPointsArchiveDTO;

public record EletronicPointsArchivePutRequest(
        Long id,
        EletronicPointsArchiveDTO eletronicPointsArchiveDTO
) {
}
