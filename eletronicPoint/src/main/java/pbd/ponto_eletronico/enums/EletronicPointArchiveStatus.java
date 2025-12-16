package pbd.ponto_eletronico.enums;

public enum EletronicPointArchiveStatus {
    Validado("validado"),
    Pendente("pendente"),
    Inconsistente("inconsistente");

    private final String status;

    EletronicPointArchiveStatus(String status){this.status = status;}

    public String getStatus(){return status;}
}

