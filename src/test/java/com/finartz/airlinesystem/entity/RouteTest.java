package com.finartz.airlinesystem.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.utility.EntityTestUtility;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;


/**
 * @author : Yekta Anil AKSOY
 * @since : 28.01.2021
 **/

class RouteTest {

    @Test
    void testRouteCreate() {
        final Route route = new Route();
        assertNotNull(route);
    }

    @Test
    void testRouteSettersAndGetters() {
        final LocalDateTime now = LocalDateTime.now();

        Route route = EntityTestUtility.getRoute();

        assertEquals(1L, route.getId());
        assertEquals("SAW", route.getDeparturePlace().getIcaoCode());
        assertEquals("TZX", route.getDestinationPlace().getIcaoCode());
        assertNotNull(route.getCreateDate());
        assertEquals(1, route.getStatus());
    }
}
