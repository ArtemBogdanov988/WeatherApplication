package ru.WeatherApplication.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.WeatherApplication.dto.MeasurementDTO;
import ru.WeatherApplication.dto.MeasurementResponse;
import ru.WeatherApplication.dto.WeatherSensorResponse;
import ru.WeatherApplication.models.Measurement;
import ru.WeatherApplication.models.WeatherSensor;
import ru.WeatherApplication.services.WeatherSensorService;
import ru.WeatherApplication.dto.WeatherSensorDTO;
import ru.WeatherApplication.util.MeasurementErrorResponse;
import ru.WeatherApplication.util.MeasurementNotCreatedException;
import ru.WeatherApplication.util.WeatherSensorValidator;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static ru.WeatherApplication.util.Errors.sendErrorToUser;

@RestController
@RequestMapping("/weather_sensors")
public class WeatherSensorsController {

    private final WeatherSensorService weatherSensorService;
    private final ModelMapper modelMapper;
    private final WeatherSensorValidator weatherSensorValidator;

    @Autowired
    public WeatherSensorsController(WeatherSensorService weatherSensorService, ModelMapper modelMapper, WeatherSensorValidator weatherSensorValidator) {
        this.modelMapper = modelMapper;
        this.weatherSensorService = weatherSensorService;
        this.weatherSensorValidator = weatherSensorValidator;
    }

    @GetMapping
    public WeatherSensorResponse getWeatherSensors() {
        return new WeatherSensorResponse(weatherSensorService.findAll().stream().map(this::convertToWeatherSensorDTO).collect(Collectors.toList()));
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid WeatherSensorDTO weatherSensorDTO, BindingResult bindingResult) {
        WeatherSensor weatherSensorToAdd = convertToWeatherSensor(weatherSensorDTO);

        weatherSensorValidator.validate(weatherSensorToAdd, bindingResult);

        if (bindingResult.hasErrors())
            sendErrorToUser(bindingResult);

                weatherSensorService.save(weatherSensorToAdd);
                return ResponseEntity.ok(HttpStatus.OK);
            }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException (MeasurementNotCreatedException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private WeatherSensor convertToWeatherSensor (WeatherSensorDTO weatherSensorDTO){
        return modelMapper.map(weatherSensorDTO, WeatherSensor.class);
    }

    private WeatherSensorDTO convertToWeatherSensorDTO(WeatherSensor weatherSensor) {
        return modelMapper.map(weatherSensor, WeatherSensorDTO.class);
    }

}