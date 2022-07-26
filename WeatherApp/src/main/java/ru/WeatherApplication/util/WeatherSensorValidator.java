package ru.WeatherApplication.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.WeatherApplication.models.WeatherSensor;
import ru.WeatherApplication.services.WeatherSensorService;

@Component
public class WeatherSensorValidator implements Validator {

    private final WeatherSensorService weatherSensorService;

    @Autowired
    public WeatherSensorValidator(WeatherSensorService weatherSensorService) {
        this.weatherSensorService = weatherSensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return WeatherSensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        WeatherSensor weatherSensor= (WeatherSensor) o;

        if (weatherSensorService.findByName(weatherSensor.getSensorName()).isPresent())
            errors.rejectValue("sensorName", "Датчик с таким именем уже существует");
    }
}