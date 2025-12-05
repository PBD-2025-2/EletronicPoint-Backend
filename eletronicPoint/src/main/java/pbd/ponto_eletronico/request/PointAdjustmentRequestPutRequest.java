package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.enums.StatusType;

public record PointAdjustmentRequestPutRequest(Long id, StatusType status) {}
