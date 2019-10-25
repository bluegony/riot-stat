package com.designPattern.h4_factoryMethod.simpleFactory;

import java.util.Objects;

/**
 * Created on 2017. 10. 11..
 */
public class SimplePizzaFactory implements PizzaFactory {
    public Pizza createPizza(String type) { // static으로 만들수도 있음
        Pizza pizza = null;
        if(Objects.equals(type,"cheese")) {
            pizza = new CheesePizza();
        } else if(Objects.equals(type, "veggie")) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }
}
