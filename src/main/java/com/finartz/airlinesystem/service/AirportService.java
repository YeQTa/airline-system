package com.finartz.airlinesystem.service;

import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

public interface AirportService {

    AirportServiceOutput createAirport(AirportServiceInput airportServiceInput);

    AirportServiceOutput getAirport(AirportServiceInput airportServiceInput);
}
