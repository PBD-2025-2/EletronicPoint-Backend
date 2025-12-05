package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.PointAdjustmentRequestDTO;
import pbd.ponto_eletronico.entity.PointAdjustmentRequest;
import pbd.ponto_eletronico.request.PointAdjustmentRequestPostRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointAdjustmentRequestMapper {
    List<PointAdjustmentRequestDTO> listPointAdjustmentRequestToListPointAdjustmentRequestDTO(List<PointAdjustmentRequest> pointAdjustmentRequests);
    PointAdjustmentRequestDTO pointAdjustmentRequestToPointAdjustmentRequestDTO(PointAdjustmentRequest pointAdjustmentRequest);
    PointAdjustmentRequest pointAdjustmentRequestDTOToPointAdjustmentRequest(PointAdjustmentRequestDTO pointAdjustmentRequest);
    PointAdjustmentRequest pointAdjustmentRequestPostRequestToPointAdjustmentRequest(PointAdjustmentRequestPostRequest pointAdjustmentRequestPostRequest);
}
