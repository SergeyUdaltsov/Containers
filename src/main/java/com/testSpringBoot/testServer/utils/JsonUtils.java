package com.testSpringBoot.testServer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Serhii_Udaltsov on 9/24/2020
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String convertObjectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static <T> T readInputStream(InputStream input, Class<T> clazz) throws IOException {
        return mapper.reader().readValue(input, clazz);
    }

    public static <T> T parseBytes (byte[] src, Class<T> clazz) {
        try {
            return mapper.readValue(src, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Parsing error");
        }
    }

    public static <T> T parseJson(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        return mapper.readValue(json, typeReference);
    }
}
