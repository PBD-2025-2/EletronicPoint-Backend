package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record RolePostRequest(
        @NotEmpty(message = "Name not found")
        String name,
        String responsibility,
        BigDecimal baseSalary,
        Long sectorId
) {}