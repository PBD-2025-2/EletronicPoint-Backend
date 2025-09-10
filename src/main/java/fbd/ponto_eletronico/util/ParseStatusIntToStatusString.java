package fbd.ponto_eletronico.util;

public interface ParseStatusIntToStatusString {
    static String ParseStatusToString(Integer status) {
        if (status == 1) {
            return "Ativo";
        }

        if (status == 2) {
            return "Fechado";
        }

        return "Pendente";
    }
}
