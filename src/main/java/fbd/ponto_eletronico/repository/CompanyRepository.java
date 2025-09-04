package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByName(String name);
    List<Company> findByCnpj(String cnpj);

}
