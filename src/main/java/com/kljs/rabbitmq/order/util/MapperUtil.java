package com.kljs.rabbitmq.order.util;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kljs.rabbitmq.order.util.deserializer.LocalDateDeserializer;
import com.kljs.rabbitmq.order.util.deserializer.LocalDateTimeDeserializer;
import com.kljs.rabbitmq.order.util.deserializer.LocalTimeDeserializer;
import com.kljs.rabbitmq.order.util.serializer.LocalDateSerializer;
import com.kljs.rabbitmq.order.util.serializer.LocalDateTimeSerializer;
import com.kljs.rabbitmq.order.util.serializer.LocalTimeSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Component
public class MapperUtil {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        SimpleModule module = new SimpleModule();

        //TODO try if current vine already support this feature.

        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());

        mapper.registerModule(module);
    }

    public Map<String, Object> readValue(String jsonString) throws IOException {
        try {
            return mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            throw e;
        }
    }

    public <T> T readValue(String content, Class<T> valueType) throws IOException {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            throw e;
        }
    }

    public <T> T readValue(String content, TypeReference valueTypeRef) throws IOException {
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw e;
        }
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw e;
        }
        return json;
    }

    public String writeValue(Object obj) {
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {

        }
        return json;
    }
}
