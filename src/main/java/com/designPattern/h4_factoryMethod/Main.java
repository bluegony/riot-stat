package com.designPattern.h4_factoryMethod;

/**
 * Created on 2017. 10. 11..
 */
public class Main {
    public static void main(String[] args) {

        // factory method 는  객체 생성 부분을 자식클래스에게 맡기는 패턴
        //

        PizzaStore nyStore = new NyPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");

        nyStore.orderPizza("veggie");
    }
}
