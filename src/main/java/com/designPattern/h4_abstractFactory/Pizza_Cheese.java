package com.designPattern.h4_abstractFactory;

/**
 * Created on 2017. 10. 13..
 */
public class Pizza_Cheese extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public Pizza_Cheese(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {

    }
}
