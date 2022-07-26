package ru.WeatherApplication.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.WeatherApplication.dto.MeasurementDTO;
import ru.WeatherApplication.dto.MeasurementResponse;
import ru.WeatherApplication.models.Measurement;
import ru.WeatherApplication.services.MeasurementService;
import ru.WeatherApplication.util.MeasurementErrorResponse;
import ru.WeatherApplication.util.MeasurementNotCreatedException;
import ru.WeatherApplication.util.MeasurementValidator;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static ru.WeatherApplication.util.Errors.sendErrorToUser;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService,
                                  MeasurementValidator measurementValidator,
                                  ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                   BindingResult bindingResult) {
        Measurement measurementToRegistration = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToRegistration, bindingResult);
        if (bindingResult.hasErrors())
            sendErrorToUser(bindingResult);

        measurementService.registerMeasurement(measurementToRegistration);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDays")
    public Long getRainyDays() {
        return measurementService.findAll().stream().filter(Measurement::getRaining).count();
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}