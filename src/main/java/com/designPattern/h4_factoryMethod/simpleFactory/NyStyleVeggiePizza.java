package com.designPattern.h4_factoryMethod.simpleFactory;

/**
 * Created on 2017. 10. 11..
 */
public class NyStyleVeggiePizza extends Pizza {
    @Override
    void prepare() {

    }

    @Override
    void bake() {
        System.out.println("bake ny veggie pizza");

    }

    @Override
    void cut() {

    }

    @Override
    void box() {

    }
}
