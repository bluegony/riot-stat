package com.study.algorithm.skpcoding.coding;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2017. 11. 21..
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        String[] s = {"specializinginintegratedandpersonalizedcommerceskplanet", "skpliziqqqzxxxxskpliz"};
        String[] k =  {"skp", "liz", "izi", "qqq", "zxxx","xsk"};

        long startTime = System.currentTimeMillis();
        long end1;
        float diff1;
        Algorithm1a.solution(k,  s );

        end1 = System.currentTimeMillis();
        diff1 = (float)(end1 - startTime) / 1000;
        log.info("elapsed time = {}", diff1);
    }
}
