package pbd.ponto_eletronico.enums;

public enum StatusType {
    Solicitado("solicitado"),
    Aprovado("aprovado"),
    Rejeitado("rejeitado");

    private final String status;

    StatusType(String status){this.status = status;}

    public String getStatus(){return status;}
}
