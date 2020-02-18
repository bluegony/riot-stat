package com.web.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static <T> T objectToClass(Object object, Class<T> valueType) {
        try {
            T obj = objectMapper.readValue(objectMapper.writeValueAsString(object), valueType);
            return obj;
        } catch (Exception e) {
            log.error("json parse error : {}", e);
            throw new RuntimeException();
        }
    }

    public static <T> T jsonToObject(String json,  Class<T> valueType)  throws IOException {
        T obj = objectMapper.readValue(json, valueType);
        return obj;
    }

    // usage :
    // facebookBatchResponseList = JsonUtils.jsonToObject(response, new TypeReference<List<FacebookBatchResponse>>(){});
    public static <T> T jsonToObject(String json,  TypeReference valueTypeRef)  throws IOException {
        T obj = objectMapper.readValue(json, valueTypeRef);
        return obj;
    }
    //
    public static <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
            List<T> ts = mapper.readValue(json, listType);
            log.debug("class name: {}", ts.get(0).getClass().getName());
            return ts;
        } catch (Exception e) {
            log.error("json parse error : {}", e);
            throw new RuntimeException();
        }
    }
}
