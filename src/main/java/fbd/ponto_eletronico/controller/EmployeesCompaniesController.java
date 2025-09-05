package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.request.CompanyPostRequest;
import fbd.ponto_eletronico.request.CompanyPutRequest;
import fbd.ponto_eletronico.service.CompanyService;
import fbd.ponto_eletronico.service.EmployeesCompaniesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employeesCompanies")
@RequiredArgsConstructor
public class EmployeesCompaniesController {
    private final EmployeesCompaniesService employeesCompaniesService;

    @GetMapping
    public ResponseEntity<List<EmployeesCompaniesDTO>> findAll(){
        return ResponseEntity.ok(employeesCompaniesService.findAll());
    }

   @GetMapping(path = "/id/{id}")
    public ResponseEntity<EmployeesCompaniesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeesCompaniesService.findById(id));
    }

}
