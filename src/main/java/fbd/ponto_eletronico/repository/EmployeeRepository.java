package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
