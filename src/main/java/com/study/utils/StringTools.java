package com.study.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {


    private static ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
    public static String prettyCommentTitle(String subject) {
        return prettyCommentTitle(subject, 150);
    }

    public static String prettyCommentTitle(String subject, int length) {
        return "\n"+ StringUtils.repeat("#",length) +"\n#"+ StringUtils.center(subject,length-2)+"#\n"+StringUtils.repeat("#",length);
    }


    public static String parseToJson(Object object){
        String json = "";
        try {
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

    public static void trimStringVariables(Object object){
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null){
                    if (value instanceof String){
                        String trimmed = (String) value;
                        trimmed = trimmed.trim();
                        trimmed = trimmed.replaceAll("\\s{4,}", "    ");
                        field.set(object, trimmed);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String maskString(String str, int maskBeginIndex, int maskCount) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        int originalStringLength = str.length();
        int begin = maskBeginIndex;
        int count = maskCount;
        if (originalStringLength < maskBeginIndex + 1) {
            begin = originalStringLength-1;
            count = 1;
        } else if (originalStringLength < maskBeginIndex + maskCount) {
            count = originalStringLength - maskBeginIndex;
        }
        return str.substring(0, begin) + StringUtils.repeat("*", count) + str.substring(begin + count, originalStringLength);
    }
    public static String masking(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer returnStrBuffer = new StringBuffer();
        if ( str.length() == 1 ) {
            returnStrBuffer.append("*");
        } else if( str.length() == 2 ) {
            returnStrBuffer.append(str.substring(0, 1)).append("*");
        } else if( str.length() > 2 ) {
            for( int i=0 ; i < str.length(); i++ ) {
                if ( i==0 || i==str.length()-1 ) {
                    returnStrBuffer.append(str.charAt(i));
                } else {
                    returnStrBuffer.append("*");
                }
            }
        }
        return returnStrBuffer.toString();
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


