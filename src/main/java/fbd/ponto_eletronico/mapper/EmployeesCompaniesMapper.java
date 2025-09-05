package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.EmployeesCompanies;
import fbd.ponto_eletronico.request.CompanyPostRequest;
import fbd.ponto_eletronico.request.CompanyPutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeesCompaniesMapper {
    EmployeesCompanies toEmployeesCompanies(EmployeesCompaniesDTO employeesCompaniesDto);

//    EmployeesCompanies toEmployeesCompanies(CompanyPostRequest companyPostRequest);

//    Company toCompany(CompanyPutRequest companyPutRequest);

    List<EmployeesCompanies> toEmployeesCompanies(List<EmployeesCompaniesDTO> employeesCompaniesDtos);

    EmployeesCompaniesDTO toEmployeesCompaniesDto(EmployeesCompanies employeesCompanies );

    List<EmployeesCompaniesDTO> toEmployeesCompaniesDtos

            (List<EmployeesCompanies> employeesCompanies);
}