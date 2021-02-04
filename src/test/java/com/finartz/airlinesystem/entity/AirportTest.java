package com.finartz.airlinesystem.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.utility.EntityTestUtility;
import org.junit.jupiter.api.Test;

/**
 * @author : Yekta Anil AKSOY
 * @since : 28.01.2021
 **/

class AirportTest {

    @Test
    void testAirportCreate() {
        final Airport airport = new Airport();
        assertNotNull(airport);
    }

    @Test
    void testAirportSettersAndGetters() {
        Airport airport = EntityTestUtility.getAirport();

        assertEquals(1L, airport.getId());
        assertEquals("istanbul", airport.getCity());
        assertEquals("SAW", airport.getIcaoCode());
        assertNotNull(airport.getCreateDate());
        assertEquals(1, airport.getStatus());
        assertEquals("Sabiha Gökçen Havalimanı", airport.getName());
    }
}
