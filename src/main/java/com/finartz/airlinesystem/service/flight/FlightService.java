package com.finartz.airlinesystem.service.flight;

import com.finartz.airlinesystem.dto.flight.FlightServiceInput;
import com.finartz.airlinesystem.dto.flight.FlightServiceOutput;
import com.finartz.airlinesystem.entity.Flight;
import java.util.List;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

public interface FlightService {

    FlightServiceOutput createFlight(FlightServiceInput flightServiceInput);

    List<FlightServiceOutput> searchFlight(FlightServiceInput flightServiceInput);

    Flight findById(Long id);
}
