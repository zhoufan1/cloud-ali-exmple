package com.example.foundation.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;

public class ArrayNullJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        if (value == null) {
            generator.writeStartArray();
            generator.writeString(StringUtils.EMPTY);
            generator.writeEndArray();
        } else {
            generator.writeObject(value);
        }
    }

}
