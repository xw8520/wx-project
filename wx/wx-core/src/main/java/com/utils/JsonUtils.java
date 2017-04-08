package com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Admin on 2016/2/21.
 */
public class JsonUtils {
    public static <T> String Serialize(T obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        return json;
    }

    public static <T> T Deserialize(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T val = mapper.readValue(json, tClass);

        return val;
    }
}
