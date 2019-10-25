package com.algorithm.skpcoding.coding;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 2017. 11. 21..
 */
@Slf4j
public class Algorithm1a {
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
    public static int countMatches(String H, String N){
        int times = 0;
        int n = H.length();
        int m = N.length();
        char[] nArray = N.toCharArray();
        char[] hArray = H.toCharArray();

        int[] pi = getPartialMatch(N);
        int matched = 0;

        for(int i=0; i<n; ++i) {
            while(matched >0 && hArray[i]!=nArray[matched]) {
                matched = pi[matched-1];
            }
            if(hArray[i] == nArray[matched]) {
                ++matched;
                if(matched == m) {
                    matched = pi[matched-1];
                    times++;
                }
            }
        }
        return times;
    }

    static int[] getPartialMatch(String N) {
        int m = N.length();
        char[] nArray = N.toCharArray();
        int[] pi  = new int[m];
        int begin = 1, matched = 0;
        while(begin + matched < m ) {
            if(nArray[begin + matched] == nArray[matched]) {
                ++matched;
                pi[begin+matched-1] = matched;
            } else {
                if(matched == 0) {
                    ++begin;
                } else {
                    begin += matched - pi[matched-1];
                    matched = pi[matched-1];
                }
            }
        }
        return pi;
    }

}
