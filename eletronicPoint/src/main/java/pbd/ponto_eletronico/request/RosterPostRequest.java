package pbd.ponto_eletronico.request;

import pbd.ponto_eletronico.enums.TypeRoster;

public interface RosterPostRequest{
    String name();
    int  weeklyWorkload();
    TypeRoster type();
}
