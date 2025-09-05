package fbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fbd.ponto_eletronico.entity.Company;
import lombok.Data;

public record RoleDTO (Long id, String name, CompanyEmbeddedDTO company){ }
