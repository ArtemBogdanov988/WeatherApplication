package ru.WeatherApplication.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.WeatherApplication.models.Measurement;
import ru.WeatherApplication.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final WeatherSensorService weatherSensorService;

    public MeasurementService(MeasurementRepository measurementRepository, WeatherSensorService weatherSensorService) {
        this.measurementRepository = measurementRepository;
        this.weatherSensorService = weatherSensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void registerMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(weatherSensorService.findByName(measurement.getSensor().getSensorName()).get());

        measurement.setRegisteredAt(LocalDateTime.now());
    }
}