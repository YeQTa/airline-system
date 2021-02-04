package com.finartz.airlinesystem.controller;

import com.finartz.airlinesystem.dto.route.RouteApiInput;
import com.finartz.airlinesystem.dto.route.RouteApiOutput;
import com.finartz.airlinesystem.dto.route.RouteServiceInput;
import com.finartz.airlinesystem.dto.route.RouteServiceOutput;
import com.finartz.airlinesystem.service.route.RouteService;
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
@RequestMapping("/api/v1/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<RouteApiOutput> createRoute(@RequestBody RouteApiInput routeApiInput) {
        final RouteServiceOutput routeServiceOutput = routeService
                .createRoute(modelMapper.map(routeApiInput, RouteServiceInput.class));

        return new ResponseEntity<>(modelMapper.map(routeServiceOutput, RouteApiOutput.class),
                HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<RouteApiOutput>> searchRoute(RouteApiInput routeApiInput) {
        final List<RouteApiOutput> routeApiOutputList = new ArrayList<>();
        routeService.searchRoute(modelMapper.map(routeApiInput, RouteServiceInput.class))
                .forEach(x -> routeApiOutputList.add(modelMapper.map(x, RouteApiOutput.class)));

        return new ResponseEntity<>(routeApiOutputList, HttpStatus.OK);
    }
}
