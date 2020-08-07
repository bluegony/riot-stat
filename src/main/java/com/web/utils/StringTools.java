package com.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
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
                        // trimm이 아닌, pfms collection 입력의 긴 데이터를 임의로 처리하는 코드. 일반 코드에서는 아래 2줄을 빼고 사용
                        trimmed = trimmed.replaceAll("\\s{4,}", "    ");
                        trimmed = trimmed.replaceAll("\\<(.*)\\>", "");
                        field.set(object, trimmed);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void removeHtmlTags(Object object){
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null){
                    if (value instanceof String){
                        String trimmed = (String) value;
                        trimmed = trimmed.replaceAll("\\<(.*)\\>", "");
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

    public static boolean isValidDateFormat(String str){
        if(str==null)
            return false;
        if(Pattern.matches("[-/:\\s0-9]+", str))
            return true;
        return false;
    }
    public static boolean isValidPhoneNumberFormat(String str){
        if(str==null)
            return false;
        if(Pattern.matches("[\\s-0-9]+", str))
            return true;
        return false;
    }

    public static Double floorTo2letter(Double original) {  // 부동소수점때문에 부정확해지는 문제가 있다. 사용시 주의
        if(original==null)
            return null;
        original = original*100;
        return Math.floor(original)/100;
    }
    public static String remove0monthText(String original) {
        return original.replaceAll("년 *0개월", "년");
    }


    public static boolean isValidStringSize(String input, String encoding, int sizeLimit) {
        try {
            log.debug("length = {}", input.getBytes(encoding).length );
            if (input!=null && input.getBytes(encoding).length > sizeLimit) {
                log.debug("return false! [{}] length > {}}. ", input, sizeLimit);
                return false;
            } else {
                log.debug("return true!");
                return true;
            }
        } catch(Exception e) {
            log.error("exception!", e);
            throw new RuntimeException();
        }
    }
}


