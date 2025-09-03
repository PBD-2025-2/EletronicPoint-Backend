package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.service.EletronicPointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/eletronicPoints")
@RequiredArgsConstructor
public class EletronicPointsController {

    private final EletronicPointsService eletronicPointsService;

    @GetMapping
    public ResponseEntity<List<EletronicPointsDTO>> listAll(){
        return ResponseEntity.ok(eletronicPointsService.listAll());
    }
}
