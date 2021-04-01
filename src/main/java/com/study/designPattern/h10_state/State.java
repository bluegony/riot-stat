package com.study.designPattern.h10_state;

/**
 * Created on 2017. 10. 12..
 */
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
