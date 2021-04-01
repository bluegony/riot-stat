package com.study.designPattern.h3_decorator;

/**
 * Created on 2017. 10. 10..
 */
public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }


    public abstract String getDescription();
}
