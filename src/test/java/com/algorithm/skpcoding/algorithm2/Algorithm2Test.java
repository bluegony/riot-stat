package com.algorithm.skpcoding.algorithm2;

import org.junit.Test;

public class Algorithm2Test {
    @Test
    public void solution() throws Exception {

        Algorithm2 a2 = new Algorithm2();
        int[][] rects = {{1, 1, 6, 5}, {2, 0, 4, 2}, {2, 4, 5, 7}, {4, 3, 8, 6}, {7, 5, 9, 7}};
        a2.solution(rects);
        int[][] rects2 = {{0, 1, 4, 4},{3, 1, 5, 3}};
        a2.solution(rects2);

    }

}