package com.designPattern.h1_strategy;

/**
 * Created on 2017. 10. 10..
 */
public abstract class Duck {
    QuackBehavior quackBehavior;

    public void performQuack() {
        quackBehavior.quack();
    }
    public void swim() {

        System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠.");
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
