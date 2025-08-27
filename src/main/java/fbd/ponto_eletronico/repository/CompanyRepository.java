package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
