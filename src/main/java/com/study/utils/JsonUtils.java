package com.study.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * Created by bluegony on 2018. 1. 29..
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    public static String parseToJson(Object object){
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("exception",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    public static String objectToJson(Object object){
        return parseToJson(object);
    }

    public static String objectToPrettyJson(Object object, boolean pretty){
        if(pretty) {
            return objectToPrettyJson(object);
        }
        return parseToJson(object);
    }

    public static String objectToPrettyJson(Object object){
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String minifyJson(String json) throws IOException {
        Object obj = objectMapper.readValue(json, Object.class);
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String json,  Class<T> valueType)  throws IOException {
        T obj = objectMapper.readValue(json, valueType);
        return obj;
    }

}
