package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.enums.RosterType;

import java.time.LocalTime;

public interface RosterPostRequest{
    String name();
    int weeklyWorkload();
    RosterType type();
    LocalTime dailyWorkloadLimit();
}
