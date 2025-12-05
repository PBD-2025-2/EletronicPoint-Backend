package pbd.ponto_eletronico.enums;

public enum RosterType {
    Diaria("diaria"),
    Plantão("plantão");

    private final String roster;

    RosterType(String roster){this.roster = roster;}

    public String getRoster(){return roster;};
}
