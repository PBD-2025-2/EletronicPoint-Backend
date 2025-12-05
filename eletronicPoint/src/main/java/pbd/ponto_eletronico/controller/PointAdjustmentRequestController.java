package pbd.ponto_eletronico.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbd.ponto_eletronico.dto.PointAdjustmentRequestDTO;
import pbd.ponto_eletronico.entity.PointAdjustmentRequest;
import pbd.ponto_eletronico.enums.StatusType;
import pbd.ponto_eletronico.request.PointAdjustmentRequestPostRequest;
import pbd.ponto_eletronico.service.PointAdjustmentRequestService;

import java.util.List;

@RestController
@RequestMapping("api/v1/PointAdjustmentRequest")
@RequiredArgsConstructor
public class PointAdjustmentRequestController {
    private final PointAdjustmentRequestService pointAdjustmentRequestService;

    @GetMapping
    public ResponseEntity<List<PointAdjustmentRequestDTO>> findAll(){
        return ResponseEntity.ok(pointAdjustmentRequestService.findAll());
    }
    @GetMapping (path = "/id/{id}")
    public ResponseEntity<PointAdjustmentRequestDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(pointAdjustmentRequestService.findById(id));
    }
    @GetMapping (path = "/status/{status}")
    public ResponseEntity<PointAdjustmentRequestDTO> findByStatus(@PathVariable StatusType status){
        return ResponseEntity.ok(pointAdjustmentRequestService.findByStatus(status));
    }
    @PostMapping (path = "/id/{eletronicPointId}")
    public ResponseEntity<PointAdjustmentRequest> save(@RequestBody PointAdjustmentRequestPostRequest pointAdjustmentRequestPostRequest, @PathVariable Long eletronicPointId){
        return new ResponseEntity<>((pointAdjustmentRequestService.save(pointAdjustmentRequestPostRequest, eletronicPointId)), HttpStatus.CREATED);
    }
    @PutMapping (path = "/id/{id}/status/{status}")
    public ResponseEntity<PointAdjustmentRequest> replace(@PathVariable Long id, @PathVariable StatusType status){
        return new ResponseEntity<>((pointAdjustmentRequestService.replace(id, status)), HttpStatus.NO_CONTENT);
    }
}
