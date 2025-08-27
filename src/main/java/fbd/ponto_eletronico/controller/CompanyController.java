package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    public ResponseEntity<List<Company>> listAll(){
        return ResponseEntity.ok(companyService.listAll());
    }
}
