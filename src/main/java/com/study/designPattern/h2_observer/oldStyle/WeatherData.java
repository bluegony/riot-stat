package com.study.designPattern.h2_observer.oldStyle;

/**
 * Created on 2017. 10. 11..
 */
public class WeatherData {

    abstract class Display {
        abstract void update(float temperature, float humidity, float pressure);
    }

    private float temperature;
    private float humidity;
    private float pressure;
    private Display display;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    // display가 추가되면 코드 수정 필요
    public void measurementsChanged() {
        display.update(getTemperature(),getHumidity(),getPressure());
    }


}
