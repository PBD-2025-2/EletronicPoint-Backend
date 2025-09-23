package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyPutRequest(@NotEmpty(message = "Name not found") String name, @CNPJ String cnpj) {}
