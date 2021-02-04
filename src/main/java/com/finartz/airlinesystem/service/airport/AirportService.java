package com.finartz.airlinesystem.service.airport;

import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;
import com.finartz.airlinesystem.entity.Airport;
import java.util.List;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

public interface AirportService {

    AirportServiceOutput createAirport(AirportServiceInput airportServiceInput);

    List<AirportServiceOutput> searchAirport(AirportServiceInput airportServiceInput);

    Airport getAirportById(Long id);
}
