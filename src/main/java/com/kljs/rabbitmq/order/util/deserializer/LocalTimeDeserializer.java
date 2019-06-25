package com.kljs.rabbitmq.order.util.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String timeStr = ((JsonNode) parser.getCodec().readTree(parser)).asText();
        return LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
