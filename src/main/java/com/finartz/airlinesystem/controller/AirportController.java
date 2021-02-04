package com.finartz.airlinesystem.controller;

import com.finartz.airlinesystem.dto.airport.AirportApiInput;
import com.finartz.airlinesystem.dto.airport.AirportApiOutput;
import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;
import com.finartz.airlinesystem.service.airport.AirportService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<AirportApiOutput> createAirport(@RequestBody AirportApiInput
            airportApiInput) {
        final AirportServiceOutput airportServiceOutput = airportService
                .createAirport(modelMapper.map(airportApiInput, AirportServiceInput.class));

        return new ResponseEntity<>(modelMapper.map(airportServiceOutput, AirportApiOutput.class),
                HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<AirportApiOutput>> searchAirport(AirportApiInput airportApiInput) {
        final List<AirportApiOutput> airportApiOutputList = new ArrayList<>();
        airportService.searchAirport(modelMapper.map(airportApiInput, AirportServiceInput.class))
                .forEach(x -> airportApiOutputList.add(modelMapper.map(x, AirportApiOutput.class)));

        return new ResponseEntity<>(airportApiOutputList, HttpStatus.OK);
    }
}
