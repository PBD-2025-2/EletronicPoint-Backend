package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> listAll(){
        return ResponseEntity.ok(companyService.listAll());
    }

   @GetMapping(path = "/id/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(companyService.findById(id));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<CompanyDTO>> findByName(@PathVariable String name){
        return ResponseEntity.ok(companyService.findByName(name));
    }

    @GetMapping(path = "/cnpj/{cnpj}")
    public ResponseEntity<List<CompanyDTO>> findByCnpj(@PathVariable String cnpj){
        return ResponseEntity.ok(companyService.findByCnpj(cnpj));
    }
}
