package com.study.designPattern.h1_strategy;

/**
 * Created on 2017. 10. 10..
 */
public class Main {

    public static void main(String[] args) {

        Duck mallard = new MallardDuck();
        mallard.swim();
        mallard.performQuack();

        System.out.println();
        Duck model = new ModelDuck();
        model.swim();
        model.performQuack();
        model.setQuackBehavior(new Bbick());
        model.performQuack();
    }
}
