package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeePutRequest(@NotEmpty(message = "Name not found") String name, @CPF String cpf) {}