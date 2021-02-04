package com.finartz.airlinesystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.spec.airport.AirportSearchCriteria;
import com.finartz.airlinesystem.spec.airport.AirportSpecs;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import java.util.List;
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
class AirportRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;

    @Test
    void givenAirport_whenSave_thenShouldReturnSavedEntity() {
        final Airport airport = EntityTestUtility.getAirport();

        final Airport savedAirport = airportRepository.save(airport);

        assertEquals(airport.getId(), savedAirport.getId());
    }

    @Test
    void givenCriterias_whenSearch_thenShouldReturnRelatedEntity() {
        final Airport airport = airportRepository.save(EntityTestUtility.getAirport());

        AirportSearchCriteria airportSearchCriteria = new AirportSearchCriteria();
        airportSearchCriteria.setId(1L);
        airportSearchCriteria.setCity("istanbul");
        airportSearchCriteria.setIcaoCode("SAW");
        airportSearchCriteria.setStatus(1);
        airportSearchCriteria.setName("Sabiha Gökçen Havalimanı");

        Specification<Airport> airportSpecs = AirportSpecs
                .findAirportByCriterias(airportSearchCriteria);

        final List<Airport> airportList = airportRepository.findAll(airportSpecs);

        assertNotNull(airportList);
        final Airport retrievedAirport = airportList.get(0);
        assertNotNull(retrievedAirport);
        assertEquals(airport.getId(), retrievedAirport.getId());
    }
}
