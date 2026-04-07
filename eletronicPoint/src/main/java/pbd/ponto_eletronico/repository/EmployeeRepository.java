package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.enums.GenderType;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findByGender(GenderType gender);
    Optional<Employee> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
