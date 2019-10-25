package com.designPattern.h1_strategy;

/**
 * Created on 2017. 10. 10..
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        quackBehavior = new MuteQuack();
    }
    public void display() {

        System.out.println("I am a ModelDuck");
    }
}
