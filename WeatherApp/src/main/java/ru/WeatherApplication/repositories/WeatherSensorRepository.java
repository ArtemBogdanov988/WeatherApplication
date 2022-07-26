package ru.WeatherApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.WeatherApplication.models.WeatherSensor;

import java.util.Optional;

@Repository
public interface WeatherSensorRepository extends JpaRepository<WeatherSensor, Integer> {
    Optional<WeatherSensor> findBySensorName(String sensorName);
}