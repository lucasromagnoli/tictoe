package br.com.lucaromagnoli.tictoe.controller;

import br.com.lucaromagnoli.tictoe.exception.ConvertJsonToObjectException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.Serializable;

public class JsonSupport {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String toJson(Object target) {
        return OBJECT_MAPPER.writeValueAsString(target);
    }

    public static <T extends Serializable> T toObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new ConvertJsonToObjectException(e);
        }
    }

    public static <T extends Serializable> T toObject(JsonParser jsonParser, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonParser, clazz);
        } catch (IOException e) {
            throw new ConvertJsonToObjectException(e);
        }
    }
}
