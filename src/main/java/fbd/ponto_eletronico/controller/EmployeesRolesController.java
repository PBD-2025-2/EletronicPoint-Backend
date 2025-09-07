package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import fbd.ponto_eletronico.service.EmployeesRolesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees_roles")
public class EmployeesRolesController {
    private final EmployeesRolesService employeesRolesService;

    @GetMapping
    public ResponseEntity<List<EmployeesRolesDTO>> listAll() {
        return ResponseEntity.ok(employeesRolesService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeesRolesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeesRolesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeesRoles> save(@RequestBody @Valid EmployeesRolesPostRequest employeesRolesPostRequest){
        return new ResponseEntity<>(employeesRolesService.save(employeesRolesPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeesRolesService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
