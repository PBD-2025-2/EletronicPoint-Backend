package fbd.ponto_eletronico.request;

import org.hibernate.validator.constraints.br.CPF;

public record EmployeePutRequest(String name, @CPF String cpf, Long companyId) {}