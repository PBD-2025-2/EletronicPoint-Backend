package serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class WorkRegimeSerialize extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer workRegime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        String result = switch (workRegime){
            case 1 -> "Padrão";

            case 2 -> "12h";

            case 3 -> "24h";

            default -> "Corrido";
        };

        jsonGenerator.writeString(result);

    }
}
