package ru.WeatherApplication.util;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String msg) {
        super(msg);
    }
}
