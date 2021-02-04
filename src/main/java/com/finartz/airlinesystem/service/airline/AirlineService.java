package com.finartz.airlinesystem.service.airline;

import com.finartz.airlinesystem.dto.airline.AirlineServiceInput;
import com.finartz.airlinesystem.dto.airline.AirlineServiceOutput;
import com.finartz.airlinesystem.entity.Airline;
import java.util.List;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/

public interface AirlineService {

    AirlineServiceOutput createAirline(AirlineServiceInput airlineServiceInput);

    List<AirlineServiceOutput> searchAirline(AirlineServiceInput airlineServiceInput);

    Airline findAirlineById(Long id);
}
