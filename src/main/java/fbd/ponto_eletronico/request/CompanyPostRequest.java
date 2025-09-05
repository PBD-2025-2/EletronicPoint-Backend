package fbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyPostRequest(@NotEmpty(message = "Name not found") String name,
                                 @NotEmpty(message = "CNPJ not found") @CNPJ String cnpj) {}
