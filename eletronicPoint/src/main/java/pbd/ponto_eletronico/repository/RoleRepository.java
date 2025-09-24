package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
    List<Role> findByCompany(Company  company);
    Boolean existsRoleByNameAndCompany(String name, Company company);
}
