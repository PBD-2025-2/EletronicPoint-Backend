package serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.util.ParseStatusIntToStatusString;

import java.io.IOException;

public class StatusSerialize extends JsonSerializer<EletronicPointsDTO> {
    @Override
    public void serialize(EletronicPointsDTO eletronicPointsDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("status", ParseStatusIntToStatusString.ParseStatusToString(eletronicPointsDTO.status()));
        jsonGenerator.writeEndObject();
    }
}
