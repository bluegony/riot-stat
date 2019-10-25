package com.designPattern.h2_observer;

/**
 * Created on 2017. 10. 10..
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 50, 20.3f);
        weatherData.setMeasurements(82, 53, 19.2f);
        weatherData.setMeasurements(84, 51, 20.0f);
    }
}
