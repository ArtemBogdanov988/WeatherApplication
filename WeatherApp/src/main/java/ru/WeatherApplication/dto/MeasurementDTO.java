package ru.WeatherApplication.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull(message = "Поле \"Значение\" не должно быть пустым")
    @Min(value = -100, message = "Зафиксирована температура ниже -100 градусов. Необходимо проверить датчик")
    @Max(value = 100, message = "Зафиксирована температура выше 100 градусов. Необходимо проверить датчик")
    private Double temperature;

    @NotNull(message = "Поле \"Дождь\" не должно быть пустым")
    private Boolean raining;

    @NotNull(message = "Поле \"Датчик\" не должно быть пустым")
    private WeatherSensorDTO sensor;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public WeatherSensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(WeatherSensorDTO sensor) {
        this.sensor = sensor;
    }
}