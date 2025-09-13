package fbd.ponto_eletronico.request;

import jakarta.validation.constraints.NotEmpty;

public record RolePutRequest(@NotEmpty(message = "Name not found")String name,
                             Long companyId) {}