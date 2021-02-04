package com.finartz.airlinesystem.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.utility.EntityTestUtility;
import org.junit.jupiter.api.Test;

/**
 * @author : Yekta Anil AKSOY
 * @since : 30.01.2021
 **/

class AirlineTest {

    @Test
    void testAirlineCreate() {
        final Airline airline = new Airline();
        assertNotNull(airline);
    }

    @Test
    void testAirlineSettersAndGetters() {
        Airline airline = EntityTestUtility.getAirline();

        assertEquals(1L, airline.getId());
        assertEquals("Pegasus Hava Yolları", airline.getName());
        assertNotNull(airline.getCreateDate());
        assertEquals(1, airline.getStatus());
    }
}
