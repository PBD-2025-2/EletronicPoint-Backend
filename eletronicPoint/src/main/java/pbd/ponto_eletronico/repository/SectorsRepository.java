package pbd.ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.entity.Sectors;

import java.util.List;

public interface SectorsRepository extends JpaRepository<Sectors, Long> {
    List<Sectors> findByName(String name);
    List<Sectors> findByCompany_Id(Long id);
    List<Sectors> findByNameAndCompany_Id(String name, Long id);
}
