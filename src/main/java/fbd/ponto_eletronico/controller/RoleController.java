package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.request.RolePostRequest;
import fbd.ponto_eletronico.request.RolePutRequest;
import fbd.ponto_eletronico.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> listAll(){
        return ResponseEntity.ok(roleService.listAll());
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<RoleDTO>> findByEmployeeName(@PathVariable String name) {
        return  ResponseEntity.ok(roleService.findByName(name));
    }

    @GetMapping(path = "/cnpj/{cnpj}")
    public ResponseEntity<List<RoleDTO>> findByCompanyName(@PathVariable String cnpj) {
        return  ResponseEntity.ok(roleService.findByCompany(cnpj));
    }

    @GetMapping(path = "/rolename/{name}/cnpj/{cnpj}")
    public ResponseEntity<List<RoleDTO>> findByRoleNameAndCnpj(@PathVariable String name, @PathVariable String cnpj){
        return ResponseEntity.ok(roleService.findByRoleNameAndCnpj(name, cnpj));
    }

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody @Valid RolePostRequest rolePostRequest) {
        return  new ResponseEntity<>(roleService.save(rolePostRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Role> replace(@PathVariable Long id, @RequestBody @Valid RolePutRequest rolePutRequest){
        return new ResponseEntity<>(roleService.replace(id, rolePutRequest), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
