package pbd.ponto_eletronico.dto;

import java.math.BigDecimal;

public record RoleDTO (
        Long id,
        String name,
        String responsibility,
        BigDecimal baseSalary,
        SectorsDTO sectors


) {
    public record Summary(
            Long id,
            String name,
            String responsibility
    ){}
}
