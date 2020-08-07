package com.web.utils;

/**
 * 문자 관련 유틸
 *
 */
public class CharUtil {
    public static boolean isEnglish(char ch){
        return (ch >= (int)'A' && ch <= (int)'Z')
            || (ch >= (int)'a' && ch <= (int)'z');
    }

    public static boolean isKorean(char ch) {
        return ch >= Integer.parseInt("AC00", 16)
                && ch <= Integer.parseInt("D7A3", 16);
    }

// 한글>영어 순서로 sort
//    @Override
//    public int compareTo(Object o) {
//        if(CharUtil.isKorean(name.charAt(0)) && !CharUtil.isKorean(o.getName().charAt(0))) {
//            return -1;
//        } else if (!CharUtil.isKorean(name.charAt(0)) && CharUtil.isKorean(o.getName().charAt(0))) {
//            return 1;
//        }
//        return name.compareTo(o.name);
//    }

    public static boolean isNumber(char ch) {
        return ch >= (int)'0' && ch <= (int)'9';
    }

    public static boolean isSpecial(char ch) {
        return (ch >= (int)'!' && ch <= (int)'/') // !"#$%&'()*+,-./
            || (ch >= (int)':' && ch <= (int)'@') //:;<=>?@
            || (ch >= (int)'[' && ch <= (int)'`') //[\]^_`
            || (ch >= (int)'{' && ch <= (int)'~'); //{|}~
    }
}
