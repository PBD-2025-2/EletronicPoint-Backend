package pbd.ponto_eletronico.controller;

import pbd.ponto_eletronico.dto.RoleDTO;
import pbd.ponto_eletronico.entity.Role;
import pbd.ponto_eletronico.request.RolePostRequest;
import pbd.ponto_eletronico.request.RolePutRequest;
import pbd.ponto_eletronico.service.RoleService;
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
    public ResponseEntity<List<RoleDTO>> findBySectorName(@PathVariable String sector) {
        return  ResponseEntity.ok(roleService.findBySector(sector));
    }

    @GetMapping(path = "/rolename/{name}/sectorName/{sectorName}")
    public ResponseEntity<List<RoleDTO>> findByRoleNameAndSectorName(@PathVariable String name, @PathVariable String sectorName){
        return ResponseEntity.ok(roleService.findByRoleNameAndSectors_Name(name, sectorName));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@RequestBody @Valid RolePostRequest rolePostRequest) {
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
