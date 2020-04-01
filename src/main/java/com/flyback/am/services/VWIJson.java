package com.flyback.am.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;



public class VWIJson extends JsonSerializer<VersionWithIdendify> {

    @Override
    public void serialize(VersionWithIdendify value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
                if (null != value) {
                    gen.writeStartObject();
                    gen.writeObjectField("identify", value.getIdentify());
                    gen.writeObjectField("version", value.getVersion());
                    gen.writeObjectField("description", value.getDescription());
                    gen.writeEndObject();
                } else {
                    gen.writeObject(false);
                }

    }
    
}