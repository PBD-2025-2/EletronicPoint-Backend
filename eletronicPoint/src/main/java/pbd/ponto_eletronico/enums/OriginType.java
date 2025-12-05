package pbd.ponto_eletronico.enums;

public enum OriginType {
    Manual("manual"),
    Importado("importado");

    private final String origin;

    OriginType(String origin){this.origin = origin;}

    public String getOrigin(){return origin;}
}
