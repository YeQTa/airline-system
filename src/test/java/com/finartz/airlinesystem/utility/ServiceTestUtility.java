package com.finartz.airlinesystem.utility;

import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import java.time.LocalDateTime;
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

}
