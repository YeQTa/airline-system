package com.finartz.airlinesystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.entity.Airline;
import com.finartz.airlinesystem.spec.airline.AirlineSearchCriteria;
import com.finartz.airlinesystem.spec.airline.AirlineSpecs;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author : Yekta Anil AKSOY
 * @since : 30.01.2021
 **/

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AirlineRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AirlineRepository airlineRepository;

    @BeforeEach
    void setUp() {
        airportRepository.save(EntityTestUtility.getAirport());
        airportRepository.save(EntityTestUtility.getDestinationAirport());
        routeRepository.save(EntityTestUtility.getRoute());
    }

    @Test
    void givenAirline_whenSave_thenShouldReturnSavedEntity() {
        final Airline airline = EntityTestUtility.getAirline();
        final Airline savedAirline = airlineRepository.save(airline);

        assertEquals(airline.getId(), savedAirline.getId());
        assertEquals(airline.getFlights().size(), savedAirline.getFlights().size());
        assertEquals(airline.getName(), savedAirline.getName());
        assertEquals(airline.getStatus(), savedAirline.getStatus());
    }

    @Test
    void givenCriterias_whenSearch_thenShouldReturnRelatedEntity() {
        final Airline airline = airlineRepository.save(EntityTestUtility.getAirline());

        AirlineSearchCriteria airlineSearchCriteria = new AirlineSearchCriteria();
        airlineSearchCriteria.setId(1L);
        airlineSearchCriteria.setStatus(1);
        airlineSearchCriteria.setName("Pegasus Hava Yolları");

        Specification<Airline> airlineSpecs = AirlineSpecs
                .findAirlineByCriterias(airlineSearchCriteria);

        final List<Airline> airlineList = airlineRepository.findAll(airlineSpecs);

        assertNotNull(airlineList);
        final Airline retrievedAirline = airlineList.get(0);
        assertNotNull(retrievedAirline);
        assertEquals(airline.getId(), retrievedAirline.getId());
        assertEquals(airline.getStatus(), retrievedAirline.getStatus());
        assertEquals(airline.getName(), retrievedAirline.getName());
    }
}
