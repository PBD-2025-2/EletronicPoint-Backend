package pbd.ponto_eletronico.mapper;

import pbd.ponto_eletronico.dto.CompanyDTO;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.request.CompanyPostRequest;
import pbd.ponto_eletronico.request.CompanyPutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toCompany(CompanyPostRequest companyPostRequest);

    Company toCompany(CompanyPutRequest companyPutRequest);

    List<Company> toCompanies(List<CompanyDTO> companyDTOS);

    CompanyDTO.Details companyToCompanyDTODetails(Company company );

    List<CompanyDTO.Summary> companyToCompanyDTOSummary(List<Company> companies);

    List<CompanyDTO> companiesToCompanyDTOs(List<Company> companies);
}