package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
    List<Role> findByCompany(Company company);
}
