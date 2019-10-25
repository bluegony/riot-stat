package com.designPattern.h2_observer;

/**
 * Created on 2017. 10. 10..
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
