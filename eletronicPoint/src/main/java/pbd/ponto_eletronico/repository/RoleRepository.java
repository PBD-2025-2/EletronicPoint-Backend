package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.entity.Sectors;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
    Boolean existsRoleByNameAndSectors(String name, Sectors sector);
    List<Role> findBySectors_Name(String sectorName);
}
