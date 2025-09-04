package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
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
    public ResponseEntity<List<RoleDTO>> findByName(@PathVariable String name) {
        return  ResponseEntity.ok(roleService.findByName(name));
    }

    @GetMapping(path = "/cnpj/{cnpj}")
    public ResponseEntity<List<RoleDTO>> findByCpf(@PathVariable String cnpj) {
        return  ResponseEntity.ok(roleService.findByCnpj(cnpj));
    }
}
