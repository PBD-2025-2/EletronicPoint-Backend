package fbd.ponto_eletronico.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StatusSerialize extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        String result = switch (status){

            case 1 -> "Aberto";

            case 2 -> "Fechado";

            default -> "Pendente";
        };
        jsonGenerator.writeString(result);

    }
}