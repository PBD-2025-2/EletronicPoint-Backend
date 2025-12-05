package pbd.ponto_eletronico.dto;

import pbd.ponto_eletronico.enums.StatusType;

public record PointAdjustmentRequestDTO(String justification, Object details, StatusType status, EletronicPointsDTO eletronicPoints) {
}