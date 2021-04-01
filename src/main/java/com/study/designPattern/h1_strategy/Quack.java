package com.study.designPattern.h1_strategy;

/**
 * Created on 2017. 10. 10..
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("qqack!");

    }
}
