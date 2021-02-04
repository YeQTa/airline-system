package com.finartz.airlinesystem.controller;

import com.finartz.airlinesystem.dto.flight.FlightApiInput;
import com.finartz.airlinesystem.dto.flight.FlightApiOutput;
import com.finartz.airlinesystem.dto.flight.FlightServiceInput;
import com.finartz.airlinesystem.dto.flight.FlightServiceOutput;
import com.finartz.airlinesystem.service.flight.FlightService;
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
@RequestMapping("/api/v1/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<FlightApiOutput> createFlight(
            @RequestBody FlightApiInput flightApiInput) {
        final FlightServiceOutput flightServiceOutput = flightService
                .createFlight(modelMapper.map(flightApiInput,
                        FlightServiceInput.class));

        return new ResponseEntity<>(modelMapper.map(flightServiceOutput, FlightApiOutput.class),
                HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<FlightApiOutput>> searchFlight(FlightApiInput flightApiInput) {
        final List<FlightApiOutput> flightApiOutputList = new ArrayList<>();
        flightService.searchFlight(modelMapper.map(flightApiInput, FlightServiceInput.class))
                .forEach(x -> flightApiOutputList.add(modelMapper.map(x, FlightApiOutput.class)));

        return new ResponseEntity<>(flightApiOutputList, HttpStatus.OK);
    }
}
