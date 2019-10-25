package com.designPattern.h4_factoryMethod;

/**
 * Created on 2017. 10. 11..
 */
public class NyStyleCheesePizza extends Pizza {
    @Override
    void prepare() {
        name = "ny style cheese pizza";
        System.out.println("preparing " + name);

    }


}
