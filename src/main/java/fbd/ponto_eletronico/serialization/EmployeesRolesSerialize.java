package fbd.ponto_eletronico.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fbd.ponto_eletronico.entity.EmployeesRoles;

import java.io.IOException;

public class EmployeesRolesSerialize extends JsonSerializer<EmployeesRoles> {
    @Override
    public void serialize(EmployeesRoles employeesRoles, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("status", employeesRoles.getStatus());
        jsonGenerator.writeNumberField("work_regime", employeesRoles.getWork_regime());
        jsonGenerator.writeStringField("employee_name", employeesRoles.getEmployee().getName());
        jsonGenerator.writeStringField("role_name", employeesRoles.getRole().getName());
        jsonGenerator.writeStringField("company_name", employeesRoles.getCompany().getName());

        jsonGenerator.writeEndObject();
    }
}
