package pbd.ponto_eletronico.controller;

import pbd.ponto_eletronico.dto.EmployeeDTO;
import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.enums.GenderType;
import pbd.ponto_eletronico.request.EmployeePostRequest;
import pbd.ponto_eletronico.request.EmployeePutRequest;
import pbd.ponto_eletronico.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
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

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<EmployeeDTO> findByCpf(@PathVariable String cpf) {
        return  ResponseEntity.ok(employeeService.findByCpf(cpf));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> findByName(@PathVariable String name) {
        return  ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping(path = "/gender/{gender}")
    public ResponseEntity<List<EmployeeDTO>> findByGender(@PathVariable GenderType gender) {
        return  ResponseEntity.ok(employeeService.findByGender(gender));
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
