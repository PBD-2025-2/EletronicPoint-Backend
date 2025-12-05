package pbd.ponto_eletronico.request;


import pbd.ponto_eletronico.dto.AdjustmentDetails;

public record PointAdjustmentRequestPostRequest(String justification, AdjustmentDetails adjustmentDetails) {
}
