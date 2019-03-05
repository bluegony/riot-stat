package com.skpcoding.algorithm2;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created by 1000773 on 2017. 11. 21..
 */
@Slf4j
public class Algorithm2 {
    final static int max_n = 1000000;
    class Edges {
        Integer[] xEdges;
        Integer[] yEdges;
        List xEdgesList;
        List yEdgesList;
        SortedSet<Integer> xsEdges;
        SortedSet<Integer> ysEdges;

        public Edges(int[][] rects) {
            int n = rects.length;
            xsEdges = new TreeSet<>();
            ysEdges = new TreeSet<>();
            for(int i=0; i<n; i++ ) {
                xsEdges.add(rects[i][0]);
                ysEdges.add(rects[i][1]);
                xsEdges.add(rects[i][2]);
                ysEdges.add(rects[i][3]);
            }
            xEdges = new Integer[xsEdges.size()];
            yEdges = new Integer[ysEdges.size()];
            xEdges = xsEdges.toArray(xEdges);
            yEdges = ysEdges.toArray(yEdges);
            xEdgesList = new ArrayList(xsEdges);
            yEdgesList = new ArrayList(ysEdges);
        }
        public long cal(int i, int j){
            return (xEdges[i+1]-xEdges[i])*(yEdges[j+1]-yEdges[j]);
        }

        public void calculateArea(int[]rect,  Map areaMap) {
            int xStart = xEdgesList.indexOf(rect[0]);
            int xEnd = xEdgesList.indexOf(rect[2]);
            int yStart = yEdgesList.indexOf(rect[1]);
            int yEnd = yEdgesList.indexOf(rect[3]);
            for(int i=xStart; i<xEnd; i++) {
                for(int j = yStart; j<yEnd; j++) {
                    areaMap.put(max_n*i+j, cal(i,j));
                }
            }
        }
    }
    public long solution(int[][] rectangles) {
        long answer = 0;
        Edges e = new Edges(rectangles);
        Map<Long,Long> areaMap = new HashMap<>();
        for(int i=0; i<rectangles.length; i++) {
            e.calculateArea(rectangles[i],  areaMap);
        }
        for(Map.Entry<Long,Long> entry :areaMap.entrySet()) {
            answer+=entry.getValue();
        }
        return answer;
    }


}
