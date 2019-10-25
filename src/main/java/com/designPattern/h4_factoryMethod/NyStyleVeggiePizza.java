package com.designPattern.h4_factoryMethod;

/**
 * Created on 2017. 10. 11..
 */
public class NyStyleVeggiePizza extends Pizza {
    @Override
    void prepare() {
        name = "ny style veggie pizza";
        System.out.println("preparing " + name);

    }


}
