package com.study.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {


    public static String prettyCommentTitle(String subject) {
        return prettyCommentTitle(subject, 150);
    }

    public static String prettyCommentTitle(String subject, int length) {
        return "\n"+ StringUtils.repeat("#",length) +"\n#"+ StringUtils.center(subject,length-2)+"#\n"+StringUtils.repeat("#",length);
    }


    public static String parseToJson(Object object){
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
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
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String minifyJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, Object.class);
        return mapper.writeValueAsString(obj);
    }


    public static boolean isMatchRegularExpression(String srcStr, String regularExpression) {
        if (srcStr == null || regularExpression == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(srcStr);
        return matcher.find();
    }


    public static String urlEncoding(String s , String encoding) {

        if ( s == null ) {
            return "";
        }

        String result = "";
        try {
            result = URLEncoder.encode(s, encoding);
            result = result.replaceAll("\\+", "%20");
        } catch(Throwable e) {
            e.printStackTrace();
        }
        return result;
    }



}


