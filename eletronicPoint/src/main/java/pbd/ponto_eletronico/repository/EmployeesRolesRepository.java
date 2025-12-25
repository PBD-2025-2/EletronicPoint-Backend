package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.entity.EmployeesRoles;
import pbd.ponto_eletronico.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRolesRepository extends JpaRepository<EmployeesRoles, Long> {

    List<EmployeesRoles> findByEmployee(Employee employee);
    List<EmployeesRoles> findByEmployeeName(String name);
    Boolean existsByRoster_IdAndEmployeeAndRole(Long idRoster, Employee employee, Role role);
}
