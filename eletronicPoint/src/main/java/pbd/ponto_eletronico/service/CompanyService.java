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


    public List<CompanyDTO.Summary> findAll(){
        List<Company> companiesData = companyRepository.findAll();
        return companyMapper.companyToCompanyDTOSummary(companiesData);
    }

    public CompanyDTO.Details findById(Long id){
        Optional<Company> companyDataById = companyRepository.findById(id);
        return companyMapper.companyToCompanyDTODetails(companyDataById
                    .orElseThrow(() -> new BadRequestException("No companies found with this ID!")));
    }

    public CompanyDTO.Details findByName(String name) {
        Company companyDataByName = companyRepository.findByName(name);
        if(companyDataByName == null){
            throw new BadRequestException("Company name not found");
        }
        return companyMapper.companyToCompanyDTODetails(companyDataByName);
    }

    public List<CompanyDTO> findByCnpj(String cnpj) {
        List<Company> companies = companyRepository.findByCnpj(cnpj);
        if(companies.isEmpty()){
            throw new BadRequestException("No companies found with this CNPJ!");
        }
        return companyMapper.companiesToCompanyDTOs(companies);
    }

    public CompanyDTO.Details findByEmail(String email){
        Company companyDataByEmail = companyRepository.findByEmail(email);
        if(companyDataByEmail == null){
            throw new BadRequestException("No company found with this E-mail!");
        }
        return companyMapper.companyToCompanyDTODetails(companyDataByEmail);
    }

    public CompanyDTO.Details findByPhoneNumber(String phoneNumber){
        Company companyDataByPhoneNumber = companyRepository.findByPhoneNumber(phoneNumber);
        if(companyDataByPhoneNumber == null){
            throw new BadRequestException("No company found with this E-mail!");
        }
        return companyMapper.companyToCompanyDTODetails(companyDataByPhoneNumber);

    }


    @Transactional
    public Company save(CompanyPostRequest companyPostRequest){
        Company company = companyMapper.toCompany(companyPostRequest);
        return companyRepository.save(company);
    }

    public Company replace(Long id, CompanyPutRequest companyPutRequest){
        Company companyData = companyRepository.getReferenceById(id);
        Company companyReplace = companyMapper.toCompany(companyPutRequest);
        companyReplace.setId(companyData.getId());
        return companyRepository.save(companyReplace);
    }

    public void delete(Long id){
        Company companyData = companyRepository.getReferenceById(id);
        companyRepository.delete(companyData);
    }
}
