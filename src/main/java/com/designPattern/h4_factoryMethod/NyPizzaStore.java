package com.designPattern.h4_factoryMethod;

import java.util.Objects;

/**
 * Created on 2017. 10. 11..
 */
public class NyPizzaStore extends PizzaStore {

    // PizzaStore 자체가 Factory
    // 상속받아 구현하는 클래스에 따라 생성되는 Pizza가 달라진다.

    @Override
    Pizza createPizza(String type) {        // 이게 factory method이고 이걸 상속받는 클래스에서 구현한다는게 핵심.
        Pizza pizza = null;
        if(Objects.equals(type,"cheese")) {
            pizza = new NyStyleCheesePizza();
        } else if(Objects.equals(type, "veggie")) {
            pizza = new NyStyleVeggiePizza();
        }
        return pizza;
    }

    // Product를 생성하는 부분에서 또 공통되는 부분을 나눠서 캡슐화하고 구성 관계로 연결하면 abstract factory method 패턴
    // 지역별 피자 생산공장별로 같은 피자를 다른 재료로 만드는 경우
    //
//    @Override
//    Pizza createPizza(String type) {
//        Pizza pizza = null;
//        PizzaIngredientFactory ingredientFactory = new NyPizzaIngredientFactory();
//        if(Objects.equals(type,"cheese")) {
//            pizza = new CheesePizza(ingredientFactory);
//        } else if(Objects.equals(type, "veggie")) {
//            pizza = new VeggiePizza(ingredientFactory);
//        }
//        return pizza;
//    }
}
