package pbd.ponto_eletronico.dto;

import pbd.ponto_eletronico.enums.TypeRoster;

public record RosterEmbeddedDTO(String name, TypeRoster type, int weeklyWorkload) {}
