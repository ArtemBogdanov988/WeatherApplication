package ru.WeatherApplication.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    @NotNull(message = "Поле \"Температура\" не должно быть пустым")
    @Min(value = -100, message = "Зафиксирована температура ниже -100 градусов. Необходимо проверить датчик")
    @Max(value = 100, message = "Зафиксирована температура выше 100 градусов. Необходимо проверить датчик")
    private Double temperature;

    @Column(name = "raining")
    @NotNull(message = "Поле \"Дождь\" не должно быть пустым")
    private Boolean raining;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @NotNull(message = "Поле \"Датчик\" не должно быть пустым")
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "sensor_name")
    private WeatherSensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public WeatherSensor getSensor() {
        return sensor;
    }

    public void setSensor(WeatherSensor sensor) {
        this.sensor = sensor;
    }
}