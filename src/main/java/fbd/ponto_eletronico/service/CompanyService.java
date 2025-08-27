package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> listAll(){
        return companyRepository.findAll();
    }

}
