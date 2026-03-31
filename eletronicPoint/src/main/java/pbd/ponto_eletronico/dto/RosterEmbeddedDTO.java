package pbd.ponto_eletronico.dto;

import pbd.ponto_eletronico.enums.RosterType;

public record RosterEmbeddedDTO(Long id, String name, RosterType type, int weeklyWorkload) {}
