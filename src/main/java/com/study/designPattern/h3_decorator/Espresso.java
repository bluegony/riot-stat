package com.study.designPattern.h3_decorator;

/**
 * Created on 2017. 10. 10..
 */
public class Espresso extends Beverage {
    public Espresso() {
        desc();
        description = "espresso";
        desc();
    }

    @Override
    public int cost() {
        return 1990;
    }
}
