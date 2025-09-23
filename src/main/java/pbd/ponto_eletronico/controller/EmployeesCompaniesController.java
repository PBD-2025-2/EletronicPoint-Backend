package pbd.ponto_eletronico.controller;

import pbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import pbd.ponto_eletronico.entity.EmployeesCompanies;
import pbd.ponto_eletronico.request.EmployeesCompaniesPostRequest;
import pbd.ponto_eletronico.service.EmployeesCompaniesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employeesCompanies")
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

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<List<EmployeesCompaniesDTO>>findByEmployee(@PathVariable String cpf){
        return ResponseEntity.ok(employeesCompaniesService.findByEmployee(cpf));
    }

    @GetMapping(path = "/cnpj/{cnpj}")
    public ResponseEntity<List<EmployeesCompaniesDTO>>findByCompany(@PathVariable String cnpj){
        return ResponseEntity.ok(employeesCompaniesService.findByCompany(cnpj));
    }

    @PostMapping
    public ResponseEntity<EmployeesCompanies> save(@RequestBody @Valid EmployeesCompaniesPostRequest employeesCompaniesPostRequest) {
        return  new ResponseEntity<>(employeesCompaniesService.save(employeesCompaniesPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeesCompaniesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
