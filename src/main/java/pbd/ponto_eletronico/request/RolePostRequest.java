package pbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record RolePostRequest(@NotEmpty(message = "Name not found") String name,
                              Long companyId) {}