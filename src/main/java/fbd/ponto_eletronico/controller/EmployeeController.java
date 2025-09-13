package fbd.ponto_eletronico.controller;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.request.CompanyPutRequest;
import fbd.ponto_eletronico.request.EmployeePostRequest;
import fbd.ponto_eletronico.request.EmployeePutRequest;
import fbd.ponto_eletronico.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> findByName(@PathVariable String name) {
        return  ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<List<EmployeeDTO>> findByCpf(@PathVariable String cpf) {
        return  ResponseEntity.ok(employeeService.findByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody @Valid EmployeePostRequest employeePostRequest) {
        return  new ResponseEntity<>(employeeService.save(employeePostRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Employee> replace(@PathVariable Long id,@RequestBody @Valid EmployeePutRequest employeePutRequest){
        return new ResponseEntity<>(employeeService.replace(id, employeePutRequest), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
