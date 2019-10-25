package com.designPattern.h1_strategy;

/**
 * Created on 2017. 10. 10..
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
    }
    public void display() {

        System.out.println("I am a MallardDuck");
    }
}
