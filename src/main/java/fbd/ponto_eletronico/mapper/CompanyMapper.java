package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<CompanyDTO> companyDtos(List<Company> companies);

    CompanyDTO companyDto(Company company );

}
