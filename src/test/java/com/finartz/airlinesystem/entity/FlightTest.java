package com.finartz.airlinesystem.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.finartz.airlinesystem.utility.EntityTestUtility;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * @author : Yekta Anil AKSOY
 * @since : 28.01.2021
 **/

class FlightTest {

    @Test
    void testFlightCreate() {
        final Flight flight = new Flight();
        assertNotNull(flight);
    }

    @Test
    void testFlightSettersAndGetters() {
        final Flight flight = EntityTestUtility.getFlight();

        assertEquals(1L, flight.getId());
        assertEquals(150, flight.getCapacity());
        assertEquals(new BigDecimal(200), flight.getPrice());
        assertNotNull(flight.getFlightDate());
        assertTrue(flight.getEstimatedArrivalTime().isAfter(flight.getFlightDate()));
        assertNotNull(flight.getRoute());
        assertEquals("SAW", flight.getRoute().getDeparturePlace().getIcaoCode());
    }
}
