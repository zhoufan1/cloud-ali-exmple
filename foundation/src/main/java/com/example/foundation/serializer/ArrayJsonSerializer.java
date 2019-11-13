package com.example.foundation.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;

public class ArrayJsonSerializer extends JsonSerializer<int[]> {
    private final Class<?>[] clazz = {Integer.class, Double.class, Float.class, Long.class, Short.class, Byte.class, Boolean.class, Character.class};

    public final static ArrayJsonSerializer INSTANCE = new ArrayJsonSerializer();

    @Override
    public void serialize(int[] value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartArray();
        if (ArrayUtils.isNotEmpty(value)) {
            for (Integer obj : value) {
                    gen.writeString(obj.toString());
            }
        }
        gen.writeEndArray();
    }
}
