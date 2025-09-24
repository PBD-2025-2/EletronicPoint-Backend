package pbd.ponto_eletronico.mapper;

import pbd.ponto_eletronico.dto.CompanyDTO;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.request.CompanyPostRequest;
import pbd.ponto_eletronico.request.CompanyPutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toCompany(CompanyDTO company);

    Company toCompany(CompanyPostRequest companyPostRequest);

    Company toCompany(CompanyPutRequest companyPutRequest);

    List<Company> toCompanies(List<CompanyDTO> companyDTOS);

    CompanyDTO toCompanyDto(Company company );

    List<CompanyDTO> toCompanyDtos(List<Company> companies);
}