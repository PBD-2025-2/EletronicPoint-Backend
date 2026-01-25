package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.entity.Sectors;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
    List<Role> findByNameAndSectors_Company_Id(String name, Long id);
    List<Role> findBySectors_Company_Id(Long id);
    List<Role> findBySectors_Company_Cnpj(String cnpj);
    Boolean existsRoleByNameAndSectors(String name, Sectors sector);
}
