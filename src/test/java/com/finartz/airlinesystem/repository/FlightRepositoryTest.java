package com.finartz.airlinesystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.entity.Flight;
import com.finartz.airlinesystem.spec.flight.FlightSearchCriteria;
import com.finartz.airlinesystem.spec.flight.FlightSpecs;
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
 * @since : 29.01.2021
 **/

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FlightRepositoryTest {

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
        airlineRepository.save(EntityTestUtility.getAirline());
    }

    @Test
    void givenFlight_whenSave_thenShouldReturnSavedEntity() {
        final Flight flight = EntityTestUtility.getFlight();
        final Flight savedFlight = flightRepository.save(flight);

        assertEquals(flight.getId(), savedFlight.getId());
        assertNotNull(savedFlight.getRoute());
        assertNotNull(savedFlight.getFlightDate());
        assertEquals(EntityTestUtility.getRoute().getDestinationPlace().getIcaoCode(),
                savedFlight.getRoute().getDestinationPlace().getIcaoCode());
    }

    @Test
    void givenCriterias_whenSearch_thenShouldReturnRelatedEntity() {
        final Flight flight = flightRepository.save(EntityTestUtility.getFlight());

        FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
        flightSearchCriteria.setId(1L);
        flightSearchCriteria.setFlightDate(EntityTestUtility.now);
        flightSearchCriteria.setDepartureCity("istanbul");
        flightSearchCriteria.setDestinationCity("trabzon");
        flightSearchCriteria.setStatus(1);

        Specification<Flight> flightSpecs = FlightSpecs.findFlightByCriterias(flightSearchCriteria);

        final List<Flight> flightList = flightRepository.findAll(flightSpecs);
        assertNotNull(flightList);
        final Flight retrievedFlight = flightList.get(0);
        assertNotNull(retrievedFlight);
        assertEquals(flight.getId(), retrievedFlight.getId());
        assertEquals(flight.getRoute().getDeparturePlace().getCity(),
                retrievedFlight.getRoute().getDeparturePlace().getCity());
        assertEquals(flight.getRoute().getDestinationPlace().getCity(),
                retrievedFlight.getRoute().getDestinationPlace().getCity());
    }
}
