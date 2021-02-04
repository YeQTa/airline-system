package com.finartz.airlinesystem.controller;

import com.finartz.airlinesystem.dto.airline.AirlineApiInput;
import com.finartz.airlinesystem.dto.airline.AirlineApiOutput;
import com.finartz.airlinesystem.dto.airline.AirlineServiceInput;
import com.finartz.airlinesystem.dto.airline.AirlineServiceOutput;
import com.finartz.airlinesystem.service.airline.AirlineService;
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
@RequestMapping("/api/v1/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<AirlineApiOutput> createAirline(
            @RequestBody AirlineApiInput airlineApiInput) {
        final AirlineServiceOutput airlineServiceOutput = airlineService
                .createAirline(modelMapper.map(airlineApiInput,
                        AirlineServiceInput.class));

        return new ResponseEntity<>(modelMapper.map(airlineServiceOutput, AirlineApiOutput.class),
                HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<AirlineApiOutput>> searchAirline(AirlineApiInput airlineApiInput) {
        final List<AirlineApiOutput> airlineApiOutputArrayList = new ArrayList<>();
        airlineService.searchAirline(modelMapper.map(airlineApiInput, AirlineServiceInput.class))
                .forEach(x -> airlineApiOutputArrayList
                        .add(modelMapper.map(x, AirlineApiOutput.class)));

        return new ResponseEntity<>(airlineApiOutputArrayList, HttpStatus.OK);
    }
}
