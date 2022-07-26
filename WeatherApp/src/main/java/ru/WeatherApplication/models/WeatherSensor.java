package ru.WeatherApplication.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Weather_sensor")
public class WeatherSensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sensor_name")
    @NotEmpty(message = "Имя датчика не должно быть пустым")
    @Size(min = 3, max = 30, message = "Имя датчика должно содержать от 3 до 30 символов включительно")
    private String sensorName;

    @Column(name = "location")
    @NotEmpty(message = "Локация не должна быть пустой")
    @Size(min = 5, max = 100, message = "Локация должна содержать от 5 до 100 символов включительно")
    private String location;

    @Column(name = "added_at")
    @NotNull
    private LocalDateTime addedAt;

    @Column(name = "updated_at")
    @NotNull
    private LocalDateTime updatedAt;

    @Column(name = "added_who")
    @NotEmpty
    private String addedWho;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAddedWho() {
        return addedWho;
    }

    public void setAddedWho(String addedWho) {
        this.addedWho = addedWho;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}