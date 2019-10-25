package com.algorithm.hackerrank;

import java.util.*;

public class MaximiseSum {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long[] maxSum = new long[t];

        for(int k=0; k<t; k++) {

            int n = in.nextInt();
            long m = in.nextLong();
            long[] input = new long[n];
            long[] precal = new long[n];
            long tempMod = 0;
            long tempMax = 0;

            TreeSet<Long> precals = new TreeSet();
            for (int i = 0; i < n; i++) {
                long number = in.nextLong();
                input[i] = number;

                tempMod = (tempMod + number) % m;
                precal[i] = tempMod;
                precals.add(tempMod);

                try {
                    tempMax = m + tempMod - precals.higher(tempMod);
                }
                catch(Exception e) {
                    tempMax = tempMod;
                }
                if (tempMax > maxSum[k]) maxSum[k] = tempMax;
            }
        }



//        for(int k=0; k<t; k++) {
//
//            int n = in.nextInt();
//            long m = in.nextLong();
//            long[] input = new long[n];
//            for (int i = 0; i < n; i++) {
//                input[i] = in.nextLong();
//            }
//            for (int i = 0; i < n; i++) {
//                long tempMax = 0;
//                for(int j=i; j<n ; j++) {
//                    tempMax = (tempMax + input[j]) % m; // i에서 j까지의 합
//                    if (tempMax > maxSum[k]) maxSum[k] = tempMax;
//                }
//            }
//
//        }


        for(int k=0; k<t; k++) {
            System.out.println(maxSum[k]);
        }

    }
}