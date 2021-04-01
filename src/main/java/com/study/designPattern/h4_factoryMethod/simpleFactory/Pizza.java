package com.study.designPattern.h4_factoryMethod.simpleFactory;

/**
 * Created on 2017. 10. 11..
 */
abstract class Pizza {
    abstract void prepare();
    abstract void bake();
    abstract void cut();
    abstract void box();
}
