package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import pbd.ponto_eletronico.dto.Address;
import pbd.ponto_eletronico.enums.GenderType;

public record EmployeePostRequest (@NotEmpty(message = "Name not found") String name,
                                   @CPF String cpf,
                                   String email,
                                   String phoneNumber,
                                   GenderType gender,
                                   Address address) {}
