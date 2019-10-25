package com.designPattern.h4_factoryMethod.simpleFactory;

/**
 * Created on 2017. 10. 11..
 */
public class PizzaStore {

    // PizzaStore가 Factory를 가지고 있고 거기서 create

    PizzaFactory factory;
    // factory 종류를 여러개 쓰고싶으면 strategy pattern을 쓸수도 있다


    public PizzaStore(PizzaFactory factory) {
        this.factory = factory;
    }
    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);

        // factory 적용하기전에는 이런식으로 만들어 썼을것임
//        if(Objects.equals(type,"cheese")) {
//            pizza = new CheesePizza();
//        } else if(Objects.equals(type, "veggie")) {
//            pizza = new VeggiePizza();
//        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public void setFactory(PizzaFactory factory) {
        this.factory = factory;
    }
}
