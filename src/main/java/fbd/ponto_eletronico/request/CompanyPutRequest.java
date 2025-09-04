package fbd.ponto_eletronico.request;

import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyPutRequest(String name, @CNPJ String cnpj) {}
