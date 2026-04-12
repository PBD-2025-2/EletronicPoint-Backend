package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
    List<Company> findByCnpj(String cnpj);

    Company findByEmail(String email);

    Company findByPhoneNumber(String phoneNumber);
}
