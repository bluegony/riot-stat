package com.study.algorithm.skpcoding.algorithm2;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2017. 11. 21..
 */
@Slf4j
public class Main {
    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();
        long end1;
        float diff1;

        Algorithm2 a2 = new Algorithm2();
        int[][] rects = {{1, 1, 6, 5}, {2, 0, 4, 2}, {2, 4, 5, 7}, {4, 3, 8, 6}, {7, 5, 9, 7}};
        a2.solution(rects);

//        int[][] rects2 = {{0, 1, 4, 4},{3, 1, 5, 3}};
//        a2.solution(rects2);

        end1 = System.currentTimeMillis();
        diff1 = (float)(end1 - startTime) / 1000;
        log.info("elapsed time = {}", diff1);
    }
}
