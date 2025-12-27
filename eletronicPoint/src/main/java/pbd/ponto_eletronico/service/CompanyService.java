package pbd.ponto_eletronico.service;


import pbd.ponto_eletronico.dto.CompanyDTO;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.CompanyMapper;
import pbd.ponto_eletronico.repository.CompanyRepository;
import pbd.ponto_eletronico.request.CompanyPostRequest;
import pbd.ponto_eletronico.request.CompanyPutRequest;
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

    public String greetingMessage() {
        return "Welcote to EletronicPoint/company application.";
    }

    public List<CompanyDTO> findAll(){
        List<Company> companies = companyRepository.findAll();
        if(companies.isEmpty()){
            throw new BadRequestException("No companies found, please register!");
        }
        return companyMapper.toCompanyDtos(companies);
    }

    public CompanyDTO findById(Long id){
        Optional<Company> company = companyRepository.findById(id);
        return companyMapper.toCompanyDto(company
                    .orElseThrow(() -> new BadRequestException("No companies found with this ID!")));
    }

    public List<CompanyDTO> findByName(String name) {
        List<Company> companies = companyRepository.findByName(name);
        if(companies.isEmpty()){
            throw new BadRequestException("No companies found with this name!");
        }
        return companyMapper.toCompanyDtos(companies);
    }

    public List<CompanyDTO> findByCnpj(String cnpj) {
        List<Company> companies = companyRepository.findByCnpj(cnpj);
        if(companies.isEmpty()){
            throw new BadRequestException("No companies found with this CNPJ!");
        }
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
