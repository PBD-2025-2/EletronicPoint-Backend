package pbd.ponto_eletronico.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbd.ponto_eletronico.dto.RoleDTO;
import pbd.ponto_eletronico.dto.SectorsDTO;
import pbd.ponto_eletronico.entity.EmployeesCompanies;
import pbd.ponto_eletronico.entity.EmployeesRoles;
import pbd.ponto_eletronico.entity.Sectors;
import pbd.ponto_eletronico.request.EmployeesCompaniesPostRequest;
import pbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import pbd.ponto_eletronico.request.SectorsPostRequest;
import pbd.ponto_eletronico.request.SectorsPutRequest;
import pbd.ponto_eletronico.service.SectorsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/sectors")
@RequiredArgsConstructor
public class SectorsController {
    private final SectorsService sectorsService;

    @GetMapping
    public ResponseEntity<List<SectorsDTO>> findAll() {
        return ResponseEntity.ok(sectorsService.findAll());
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<SectorsDTO> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(sectorsService.findById(id));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<SectorsDTO>> findByName(@PathVariable String name) {
        return  ResponseEntity.ok(sectorsService.findByName(name));
    }

    @GetMapping(path = "/name/{name}/cnpj/{cnpj}")
    public ResponseEntity<List<SectorsDTO>> findByNameAndCnpj(@PathVariable String name, @PathVariable String cnpj) {
        return  ResponseEntity.ok(sectorsService.findByNameAndCnpj(name, cnpj));
    }

//    @GetMapping(path = "/sectorsName/{name}")
//    public ResponseEntity<List<RoleDTO>> findAllRolesInSector(@PathVariable String name) {
//        return  ResponseEntity.ok(sectorsService.findAllRolesInSector(name));
//    }

    @PostMapping
    public ResponseEntity<Sectors> save(@RequestBody @Valid SectorsPostRequest sectorsPostRequest) {
        return  new ResponseEntity<>(sectorsService.save(sectorsPostRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/id{id}")
    public ResponseEntity<SectorsDTO> replace(@Valid @PathVariable Long id, @RequestBody SectorsPutRequest sectorsPutRequest){
        return new ResponseEntity<>(sectorsService.replace(id, sectorsPutRequest), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sectorsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
