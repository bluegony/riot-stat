package com.designPattern.h8_templateMethod;

/**
 * Created on 2017. 10. 11..
 */
public class Main {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        coffee.make();

        Beverage tea = new Tea();
        tea.make();
    }
}
