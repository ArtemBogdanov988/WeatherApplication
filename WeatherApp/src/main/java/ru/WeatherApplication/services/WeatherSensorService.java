package ru.WeatherApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.WeatherApplication.models.Measurement;
import ru.WeatherApplication.models.WeatherSensor;
import ru.WeatherApplication.repositories.WeatherSensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WeatherSensorService {

    private final WeatherSensorRepository weatherSensorRepository;

    @Autowired
    public WeatherSensorService(WeatherSensorRepository weatherSensorRepository) {
        this.weatherSensorRepository = weatherSensorRepository;
    }

    public List<WeatherSensor> findAll() {
        return weatherSensorRepository.findAll();
    }

    public Optional<WeatherSensor> findByName(String sensorName) {
        return weatherSensorRepository.findBySensorName(sensorName);
    }

    @Transactional
    public void save(WeatherSensor weatherSensor) {
        enrichWeatherSensor(weatherSensor);
        weatherSensorRepository.save(weatherSensor);
    }

    private void enrichWeatherSensor(WeatherSensor weatherSensor) {
        weatherSensor.setAddedAt(LocalDateTime.now());
        weatherSensor.setUpdatedAt(LocalDateTime.now());
        weatherSensor.setAddedWho("ADMIN");
    }
}