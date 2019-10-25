package com.designPattern.h4_factoryMethod;

/**
 * Created on 2017. 10. 11..
 */
public class NyPizzaIngredientFactory implements PizzaIngredientFactory {

    /**
     * abstract factory
     */

    @Override
    public void createDough() {
        System.out.println("create ny dough");
    }

    @Override
    public void createSauce() {
        System.out.println("create ny sauce ");

    }
}
