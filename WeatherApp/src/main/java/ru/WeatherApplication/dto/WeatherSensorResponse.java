package ru.WeatherApplication.dto;

import java.util.List;

public class WeatherSensorResponse {
    private List<WeatherSensorDTO> weatherSensors;

    public WeatherSensorResponse(List<WeatherSensorDTO> weatherSensors) {
        this.weatherSensors = weatherSensors;
    }

    public List<WeatherSensorDTO> getWeatherSensors() {
        return weatherSensors;
    }

    public void setWeatherSensors(List<WeatherSensorDTO> weatherSensors) {
        this.weatherSensors = weatherSensors;
    }
}
