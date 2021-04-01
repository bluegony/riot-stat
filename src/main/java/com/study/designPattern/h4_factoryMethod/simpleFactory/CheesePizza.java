package com.study.designPattern.h4_factoryMethod.simpleFactory;

/**
 * Created on 2017. 10. 11..
 */
public class CheesePizza extends Pizza {
    @Override
    void prepare() {

    }

    @Override
    void bake() {
        System.out.println("bake cheese pizza");

    }

    @Override
    void cut() {

    }

    @Override
    void box() {

    }
}
