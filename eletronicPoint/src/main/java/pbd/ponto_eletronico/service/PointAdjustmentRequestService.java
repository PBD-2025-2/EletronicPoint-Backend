package pbd.ponto_eletronico.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbd.ponto_eletronico.dto.EletronicPointsDTO;
import pbd.ponto_eletronico.dto.PointAdjustmentRequestDTO;
import pbd.ponto_eletronico.entity.EletronicPoints;
import pbd.ponto_eletronico.entity.PointAdjustmentRequest;
import pbd.ponto_eletronico.enums.StatusType;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.EletronicPointsMapper;
import pbd.ponto_eletronico.mapper.PointAdjustmentRequestMapper;
import pbd.ponto_eletronico.repository.PointAdjustmentRequestRepository;
import pbd.ponto_eletronico.request.PointAdjustmentRequestPostRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointAdjustmentRequestService {
    private final PointAdjustmentRequestRepository pointAdjustmentRequestRepository;
    private final PointAdjustmentRequestMapper pointAdjustmentRequestMapper;
    private final EletronicPointsService eletronicPointsService;
    private final EletronicPointsMapper eletronicPointsMapper;

    public List<PointAdjustmentRequestDTO> findAll(){
        List<PointAdjustmentRequest> pointAdjustmentRequestData = pointAdjustmentRequestRepository.findAll();
        if(pointAdjustmentRequestData.isEmpty()){
            throw new BadRequestException("there are no Request from point adjustment");
        }
        return pointAdjustmentRequestMapper.listPointAdjustmentRequestToListPointAdjustmentRequestDTO(pointAdjustmentRequestData);
    }
    public PointAdjustmentRequestDTO findById(Long id){
        Optional<PointAdjustmentRequest> pointAdjustmentRequestData = pointAdjustmentRequestRepository.findById(id);
        return pointAdjustmentRequestMapper.pointAdjustmentRequestToPointAdjustmentRequestDTO(pointAdjustmentRequestData.
                orElseThrow(() -> new BadRequestException("Id Not Found")));
    }
    public PointAdjustmentRequestDTO findByStatus(StatusType status){
        Optional<PointAdjustmentRequest> pointAdjustmentRequestData = pointAdjustmentRequestRepository.findByStatus(status);
        return pointAdjustmentRequestMapper.pointAdjustmentRequestToPointAdjustmentRequestDTO(pointAdjustmentRequestData.
                orElseThrow(() -> new BadRequestException("Status Not Found")));
    }

    public PointAdjustmentRequest save (PointAdjustmentRequestPostRequest pointAdjustmentRequestPostRequest, Long eletronicPointId){
        EletronicPoints eletronicPointsData = eletronicPointsMapper.toEletronicPoints(eletronicPointsService.findById(eletronicPointId));
        PointAdjustmentRequest pointAdjustmentRequestData = pointAdjustmentRequestMapper.pointAdjustmentRequestPostRequestToPointAdjustmentRequest(pointAdjustmentRequestPostRequest);
        pointAdjustmentRequestData.setEletronicPoints(eletronicPointsData);
        pointAdjustmentRequestData.setDetails(pointAdjustmentRequestPostRequest.adjustmentDetails());
        pointAdjustmentRequestData.setStatus(StatusType.Solicitado);
        return pointAdjustmentRequestRepository.save(pointAdjustmentRequestData);

    }

    public PointAdjustmentRequest replace(Long id, StatusType status){
        PointAdjustmentRequest pointAdjustmentRequestData = pointAdjustmentRequestMapper.pointAdjustmentRequestDTOToPointAdjustmentRequest(findById(id));
        pointAdjustmentRequestData.setId(id);
        pointAdjustmentRequestData.setStatus(status);
        return pointAdjustmentRequestRepository.save(pointAdjustmentRequestData);
    }

}
