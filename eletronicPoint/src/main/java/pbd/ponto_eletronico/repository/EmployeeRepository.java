package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findByCpf(String cpf);
}
