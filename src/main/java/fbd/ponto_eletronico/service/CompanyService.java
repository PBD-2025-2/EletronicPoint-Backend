package fbd.ponto_eletronico.service;


import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.repository.CompanyRepository;
import fbd.ponto_eletronico.request.CompanyPostRequest;
import fbd.ponto_eletronico.request.CompanyPutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyDTO> findAll(){
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toCompanyDtos(companies);
    }

    public CompanyDTO findById(Long id){
        Optional<Company> company = companyRepository.findById(id);
        return companyMapper.toCompanyDto(company
                    .orElseThrow(() ->new BadRequestException("Id Not Found")));
    }

    public List<CompanyDTO> findByName(String name) {
        List<Company> companies = companyRepository.findByName(name);
        return companyMapper.toCompanyDtos(companies);
    }

    public List<CompanyDTO> findByCnpj(String cnpj) {
        List<Company> companies = companyRepository.findByCnpj(cnpj);
        return companyMapper.toCompanyDtos(companies);
    }

    @Transactional
    public Company save(CompanyPostRequest companyPostRequest){
        Company company = companyMapper.toCompany(companyPostRequest);
        return companyRepository.save(company);
    }

    public Company replace(Long id, CompanyPutRequest companyPutRequest){
        Company companyData = companyMapper.toCompany(findById(id));
        Company companyReplace = companyMapper.toCompany(companyPutRequest);
        companyReplace.setId(companyData.getId());
        return companyRepository.save(companyReplace);
    }

    public void delete(Long id){
        Company companyData = companyMapper.toCompany(findById(id));
        companyRepository.delete(companyData);
    }
}
