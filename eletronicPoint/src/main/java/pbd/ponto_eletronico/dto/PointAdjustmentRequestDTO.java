package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pbd.ponto_eletronico.enums.StatusType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PointAdjustmentRequestDTO(String justification, Object details, StatusType status, EletronicPointsDTO eletronicPoints) {
}