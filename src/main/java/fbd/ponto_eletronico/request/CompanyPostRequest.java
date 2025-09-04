package fbd.ponto_eletronico.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyPostRequest(String name,@CNPJ String cnpj) {}
