package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.service.EmployeesRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles_employees")
public class EmployeesRolesController {
    private final EmployeesRolesService employeesRolesService;

    @GetMapping
    public ResponseEntity<List<EmployeesRolesDTO>> listAll() {
        return ResponseEntity.ok(employeesRolesService.listAll());
    }
}
