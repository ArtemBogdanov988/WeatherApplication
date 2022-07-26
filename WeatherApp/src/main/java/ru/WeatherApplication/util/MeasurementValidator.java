package ru.WeatherApplication.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.WeatherApplication.models.Measurement;
import ru.WeatherApplication.services.WeatherSensorService;

@Component
public class MeasurementValidator implements Validator {

    private final WeatherSensorService weatherSensorService;

    @Autowired
    public MeasurementValidator(WeatherSensorService weatherSensorService) {
        this.weatherSensorService = weatherSensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSensor() == null) {
            return;
        }

        if (weatherSensorService.findByName(measurement.getSensor().getSensorName()).isEmpty())
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем");
    }
}