package pbd.ponto_eletronico.controller;

import pbd.ponto_eletronico.dto.EmployeesRolesDTO;
import pbd.ponto_eletronico.entity.EmployeesRoles;
import pbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import pbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import pbd.ponto_eletronico.service.EmployeesRolesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees_roles")
public class EmployeesRolesController {
    private final EmployeesRolesService employeesRolesService;

    @GetMapping
    public ResponseEntity<List<EmployeesRolesDTO>> listAll() {
        return ResponseEntity.ok(employeesRolesService.listAll());
    }

    @GetMapping(path = "id/{id}")
    public ResponseEntity<EmployeesRolesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeesRolesService.findById(id));
    }

    @GetMapping(path = "cpf/{cpf}")
    public ResponseEntity<List<EmployeesRolesDTO>> findByEmployee(@PathVariable String cpf){
        return ResponseEntity.ok(employeesRolesService.findByEmployee(cpf));
    }

    @GetMapping(path = "name/{name}")
    public ResponseEntity<List<EmployeesRolesDTO>> findByEmployeeName(@PathVariable String name){
        return ResponseEntity.ok(employeesRolesService.findByEmployeeName(name));
    }

    @GetMapping(path = "cpf/{cpf}/roleName/{roleName}")
    public ResponseEntity<List<EmployeesRolesDTO>> findByEmployeeRole(@PathVariable String cpf, @PathVariable String roleName){
        return ResponseEntity.ok(employeesRolesService.findByEmployeeRole(cpf, roleName));
    }

    @PostMapping
    public ResponseEntity<EmployeesRoles> save(@RequestBody @Valid EmployeesRolesPostRequest employeesRolesPostRequest){
        return new ResponseEntity<>(employeesRolesService.save(employeesRolesPostRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/id{id}")
    public ResponseEntity<EmployeesRoles> replace(@Valid @PathVariable Long id,@RequestBody EmployeesRolesPutRequest employeesRolesPutRequest){
        return new ResponseEntity<>(employeesRolesService.replace(id, employeesRolesPutRequest), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeesRolesService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
