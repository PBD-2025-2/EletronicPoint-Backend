package pbd.ponto_eletronico.dto;

public record Address(
        String street,
        String neighborhood,
        String complement,
        Integer number,
        String postalNumber,
        String city,
        String state,
        String country
){}