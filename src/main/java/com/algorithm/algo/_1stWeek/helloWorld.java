package com.algorithm.algo._1stWeek;

import java.util.Scanner;
public class helloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while(cases-- > 0) {
            String name = sc.next();
            System.out.println("Hello, " + name + "!");
        }
    }
}