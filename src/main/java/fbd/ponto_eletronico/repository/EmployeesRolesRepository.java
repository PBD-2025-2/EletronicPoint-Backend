package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRolesRepository extends JpaRepository<EmployeesRoles, Long> {

    List<EmployeesRoles> findByEmployee(Employee employee);
    Boolean existsByWorkRegimeAndEmployeeAndRole(int workRegime, Employee employee, Role role);
}
