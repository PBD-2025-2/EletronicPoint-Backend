package fbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record RolePostRequest(@NotEmpty(message = "Name not found") String name,
                              @NotEmpty(message = "Id not found") Long companyId) {}