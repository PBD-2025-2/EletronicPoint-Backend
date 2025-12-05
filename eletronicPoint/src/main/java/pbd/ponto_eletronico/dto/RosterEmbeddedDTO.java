package pbd.ponto_eletronico.dto;

import pbd.ponto_eletronico.enums.RosterType;

public record RosterEmbeddedDTO(String name, RosterType type, int weeklyWorkload) {}
