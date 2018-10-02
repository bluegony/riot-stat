package com.skpcoding.coding;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by 1000773 on 2017. 11. 20..
 */
@Slf4j
public class Algorithm1 {
    public static void main(String[] args) {
        String[] s = {"specializinginintegratedandpersonalizedcommerceskplanet", "skpliziqqqzxxxxskpliz"};
        String[] k =  {"skp", "liz", "izi", "qqq", "zxxx","xsk"};

        solution(k,  s );
    }
    public static String solution(String[] K, String[] S) {
        String answer = "";
        Collection<String> strings = Arrays.asList(S);
        Collection<String> keywards = Arrays.asList(K);
        int allcount = 0;
        for(String string : strings) {
            for (String keyward : keywards) {
                allcount += countMatches(string,keyward);
            }
            answer += allcount;
            log.info(answer);

            allcount = 0;

        }
        return answer;
    }
    public static int countMatches(String mainString, String whatToFind){
//        log.info("{},{}",mainString, whatToFind);
        int times = 0;
        int matchIndex = 0;
        int keywordLength = whatToFind.length();
        char[] keywordArray = whatToFind.toCharArray();
        for (char ch: mainString.toCharArray()) {
            if (ch == keywordArray[matchIndex]) {
                matchIndex++;
                if(matchIndex == keywordLength) {
                    times++;
                    matchIndex = 0;
                }
            } else if(ch == keywordArray[0]) {
                matchIndex = 1;
                if(matchIndex == keywordLength) {
                    times++;
                    matchIndex = 0;
                }
            } else {
                matchIndex = 0;
            }
        }
        return times;
    }

//    public static int countMatches(String mainString, String whatToFind){
//        String tempString = mainString.replaceAll(whatToFind, "");
//        //this even work for on letter
//        log.info("{},{}",mainString, whatToFind);
//        int times = (mainString.length()-tempString.length())/whatToFind.length();
//
//        //times should be 4
//        return times;
//    }
}
