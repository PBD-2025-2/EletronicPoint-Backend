package fbd.ponto_eletronico.service;


import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyDTO> listAll(){
        List<Company> companies = companyRepository.findAll();
        return companyMapper.companyDtos(companies);
    }

    public CompanyDTO findById(Long id){

        return companyRepository.findById(id)
                .map(companyMapper :: companyDto)
                .orElseThrow(() ->new BadRequestException("Id Not Found"));
    }

}
