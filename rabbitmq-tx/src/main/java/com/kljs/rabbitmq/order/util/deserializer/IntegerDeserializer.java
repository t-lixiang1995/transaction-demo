package com.kljs.rabbitmq.order.util.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class IntegerDeserializer extends JsonDeserializer<Integer> {
    public static final String CLOSED_WARNING = "close_exceed_max_int_warning";

    @Override
    public Integer deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        String data = ((JsonNode) parser.getCodec().readTree(parser)).asText();
        if (!(data.isEmpty()||data==null) && data.length() == 10) {
        }

        return (data.isEmpty()||data==null) ? null : Integer.parseInt(data) ;
    }
}
