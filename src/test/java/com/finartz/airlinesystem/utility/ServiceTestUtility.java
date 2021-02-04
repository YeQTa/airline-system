package com.finartz.airlinesystem.utility;

import com.finartz.airlinesystem.dto.airline.AirlineServiceInput;
import com.finartz.airlinesystem.dto.airline.AirlineServiceOutput;
import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import com.finartz.airlinesystem.dto.flight.FlightServiceInput;
import com.finartz.airlinesystem.dto.route.RouteServiceInput;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

public final class ServiceTestUtility {

    public final static LocalDateTime now = LocalDateTime.now();
    public final static UUID ticketCode = UUID.randomUUID();

    public static AirportServiceInput getAirportServiceInput() {
        AirportServiceInput airportServiceInput = new AirportServiceInput();
        airportServiceInput.setCity("istanbul");
        airportServiceInput.setIcaoCode("SAW");
        airportServiceInput.setName("Sabiha Gökçen Havalimanı");
        return airportServiceInput;
    }

    public static RouteServiceInput getRouteServiceInput() {
        RouteServiceInput routeServiceInput = new RouteServiceInput();
        routeServiceInput.setDeparturePlace(1L);
        routeServiceInput.setDestinationPlace(2L);
        routeServiceInput.setFlyingTime(LocalTime.of(1, 30));
        return routeServiceInput;
    }

    public static FlightServiceInput getFlightServiceInput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("10/02/2021 20:00", formatter);
        FlightServiceInput flightServiceInput = new FlightServiceInput();
        flightServiceInput.setRoute(1L);
        flightServiceInput.setId(1L);
        flightServiceInput.setAirline(1L);
        flightServiceInput.setCapacity(30);
        flightServiceInput.setFlightDate(localDateTime);
        flightServiceInput.setPrice(new BigDecimal("200"));
        return flightServiceInput;
    }

    public static AirlineServiceInput getAirlineServiceInput() {
        AirlineServiceInput airlineServiceInput = new AirlineServiceInput();
        airlineServiceInput.setId(1L);
        airlineServiceInput.setName("Pegasus Hava Yolları");
        return airlineServiceInput;
    }

    public static AirlineServiceOutput getAirlineServiceOutput() {
        AirlineServiceOutput airlineServiceOutput = new AirlineServiceOutput();
        airlineServiceOutput.setId(1L);
        airlineServiceOutput.setName("Pegasus Hava Yolları");
        airlineServiceOutput.setStatus(1);
        return airlineServiceOutput;
    }
}
