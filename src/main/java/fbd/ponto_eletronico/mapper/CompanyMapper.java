package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.CompanyDTO;
import fbd.ponto_eletronico.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<CompanyDTO> companyDtos(List<Company> companies);
}
