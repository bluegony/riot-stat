package com.designPattern.h8_templateMethod;

/**
 * Created on 2017. 10. 11..
 */
public class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("찻주전자에 우전 녹차를 넣고 추출합니다.");
    }

    @Override
    void addCondiments() {
        System.out.println("녹차는 스트레이트!");


    }
}
