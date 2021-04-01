package com.study.designPattern.h4_factoryMethod.simpleFactory;

/**
 * Created on 2017. 10. 11..
 */
public class VeggiePizza extends Pizza {
    @Override
    void prepare() {

    }

    @Override
    void bake() {
        System.out.println("bake veggie pizza");

    }

    @Override
    void cut() {

    }

    @Override
    void box() {

    }
}
