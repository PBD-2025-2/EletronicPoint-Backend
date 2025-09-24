package pbd.ponto_eletronico.mapper;

import pbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import pbd.ponto_eletronico.entity.EmployeesCompanies;
import pbd.ponto_eletronico.request.EmployeesCompaniesPostRequest;
import pbd.ponto_eletronico.request.EmployeesCompaniesPutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeesCompaniesMapper {
    EmployeesCompanies toEmployeesCompanies(EmployeesCompaniesDTO employeesCompaniesDto);

    EmployeesCompanies toEmployeesCompanies(EmployeesCompaniesPostRequest employeesCompaniesPostRequest);

    EmployeesCompanies toEmployeesCompanies(EmployeesCompaniesPutRequest employeesCompaniesPutRequest);

    List<EmployeesCompanies> toEmployeesCompanies(List<EmployeesCompaniesDTO> employeesCompaniesDtos);

    EmployeesCompaniesDTO toEmployeesCompaniesDto(EmployeesCompanies employeesCompanies );

    List<EmployeesCompaniesDTO> toEmployeesCompaniesDtos(List<EmployeesCompanies> employeesCompanies);
}