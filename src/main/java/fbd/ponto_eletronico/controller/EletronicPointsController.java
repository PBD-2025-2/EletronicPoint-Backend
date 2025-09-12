package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.entity.EletronicPoints;
import fbd.ponto_eletronico.request.EletronicPointsPutRequest;
import fbd.ponto_eletronico.service.EletronicPointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/eletronicPoints")
@RequiredArgsConstructor
public class EletronicPointsController {

    private final EletronicPointsService eletronicPointsService;

    @GetMapping
    public ResponseEntity<List<EletronicPointsDTO>> listAll(){
        return ResponseEntity.ok(eletronicPointsService.listAll());
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<EletronicPointsDTO> findByID(@PathVariable Long id) {
        return ResponseEntity.ok(eletronicPointsService.findById(id));
    }

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<List<EletronicPointsDTO>> findByEmployee(@PathVariable String cpf) {
        return ResponseEntity.ok(eletronicPointsService.findByEmployee(cpf));
    }

    @GetMapping(path = "/cnpj/{cnpj}")
    public ResponseEntity<List<EletronicPointsDTO>> findByCompany(@PathVariable String cnpj) {
        return ResponseEntity.ok(eletronicPointsService.findByCompany(cnpj));
    }

    @GetMapping(path = "/localDate/{localDate}")
    public ResponseEntity<List<EletronicPointsDTO>> findByDate(@PathVariable LocalDate localDate) {
        return ResponseEntity.ok(eletronicPointsService.findByBate(localDate));
    }

    @GetMapping(path = "/localDate/{startDate}/localDate/{endDate}")
    public ResponseEntity<List<EletronicPointsDTO>> findByStartDateAndEndDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return ResponseEntity.ok(eletronicPointsService.findByStartDateAndEndDate(startDate, endDate));
    }
    @PostMapping(path = "/id/{employeesRolesId}")
    public ResponseEntity<EletronicPointsDTO> register(@RequestBody @PathVariable Long employeesRolesId) {
        return new ResponseEntity<>(eletronicPointsService.register(employeesRolesId), HttpStatus.CREATED);
    }
    @PutMapping(path = "/id/{eletronicPointsId}")
    public ResponseEntity<EletronicPointsDTO> replace (@RequestBody @PathVariable Long eletronicPointsId, @RequestBody EletronicPointsPutRequest eletronicPointsPutRequest){
        return new ResponseEntity<>(eletronicPointsService.replace(eletronicPointsId, eletronicPointsPutRequest), HttpStatus.NO_CONTENT);
    }

}
