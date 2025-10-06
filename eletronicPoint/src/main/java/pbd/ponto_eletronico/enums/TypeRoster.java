package pbd.ponto_eletronico.enums;

public enum TypeRoster {
    Diaria("diaria"),
    Plantão("plantão");

    private String roster;

    TypeRoster(String roster){this.roster = roster;}

    public String getRoster(){return roster;};
}
