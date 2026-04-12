package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import pbd.ponto_eletronico.dto.Address;

public record CompanyPutRequest(
        @NotEmpty(message = "Name not found")
        String name,
        @CNPJ
        String cnpj,
        Address address,
        String email,
        String phoneNumber) {}
