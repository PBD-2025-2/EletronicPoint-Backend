package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.enums.RosterType;

public interface RosterPostRequest{
    String name();
    int  weeklyWorkload();
    RosterType type();
}
