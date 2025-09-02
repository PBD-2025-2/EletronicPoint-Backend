package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> listAll(){
        return ResponseEntity.ok(companyService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(companyService.findById(id));
    }

}
