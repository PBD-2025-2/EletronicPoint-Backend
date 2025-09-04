package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.request.CompanyPostRequest;
import fbd.ponto_eletronico.request.CompanyPutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toCompany(CompanyDTO company );

    Company toCompany(CompanyPostRequest companyPostRequest);

    Company toCompany(CompanyPutRequest companyPutRequest);

    CompanyDTO toCompanyDto(Company company );

    List<CompanyDTO> toCompanyDtos(List<Company> companies);
}