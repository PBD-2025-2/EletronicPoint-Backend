package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.RolesEmployeesDTO;
import fbd.ponto_eletronico.service.RolesEmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles_employees")
public class RolesEmployeesController {
    private final RolesEmployeesService rolesEmployeesService;

    @GetMapping
    public ResponseEntity<List<RolesEmployeesDTO>> listAll() {
        return ResponseEntity.ok(rolesEmployeesService.listAll());
    }
}
