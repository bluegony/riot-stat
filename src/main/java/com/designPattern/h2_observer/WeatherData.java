package com.designPattern.h2_observer;

import java.util.ArrayList;

/**
 * Created on 2017. 10. 10..
 */
public class WeatherData implements Subject {
    // WeatherData class에는 getTemperature(), getHumidity(), getPressure(), measuermentsChanged() 메소드가 있음
    // measurementsChanged()가 호출되면 get~()함수들을 이용해서 디스플레이를 변경하는 작업을 구현해야함

    private ArrayList<Observer> observsers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observsers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observsers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {
        int i = observsers.indexOf(o);
        if(i>=0) {
            observsers.remove(i);
        }

    }

    @Override
    public void notifyObservers() {
        for(Observer o : observsers) {
            o.update(getTemperature(),getHumidity(),getPressure());
        }

    }

    public void measuermentsChanged(){
        notifyObservers();
    }


    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measuermentsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    // 기타 WeatherData 메소드

}
