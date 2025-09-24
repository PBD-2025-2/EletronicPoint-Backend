package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeePostRequest (@NotEmpty(message = "Name not found") String name,
                                   @CPF String cpf) {}
