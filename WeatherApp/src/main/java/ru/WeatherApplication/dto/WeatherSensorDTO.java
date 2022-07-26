package ru.WeatherApplication.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class WeatherSensorDTO {
    @NotEmpty(message = "Имя датчика не должно быть пустым")
    @Size(min = 3, max = 30, message = "Имя датчика должно содержать от 3 до 30 символов включительно")
    private String sensorName;

    @NotEmpty(message = "Локация не должна быть пустой")
    @Size(min = 5, max = 100, message = "Локация должна содержать от 5 до 100 символов включительно")
    private String location;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
