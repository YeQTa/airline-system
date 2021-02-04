package com.finartz.airlinesystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.entity.Route;
import com.finartz.airlinesystem.spec.route.RouteSearchCriteria;
import com.finartz.airlinesystem.spec.route.RouteSpecs;
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
class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AirportRepository airportRepository;

    @BeforeEach
    void setUp() {
        airportRepository.save(EntityTestUtility.getAirport());
        airportRepository.save(EntityTestUtility.getDestinationAirport());
    }

    @Test
    void givenRoute_whenSave_thenShouldReturnSavedEntity() {
        final Route route = EntityTestUtility.getRoute();
        final Route savedRoute = routeRepository.save(route);

        assertEquals(route.getId(), savedRoute.getId());
        assertNotNull(savedRoute.getDeparturePlace());
        assertNotNull(savedRoute.getDestinationPlace());
    }

    @Test
    void givenCriterias_whenSearch_thenShouldReturnRelatedEntity() {
        final Route route = routeRepository.save(EntityTestUtility.getRoute());

        RouteSearchCriteria routeSearchCriteria = new RouteSearchCriteria();
        routeSearchCriteria.setId(1L);
        routeSearchCriteria.setDeparturePlace(EntityTestUtility.getAirport().getId());
        routeSearchCriteria
                .setDestinationPlace(EntityTestUtility.getDestinationAirport().getId());
        routeSearchCriteria.setStatus(1);

        Specification<Route> routeSpecs = RouteSpecs.findRouteByCriterias(routeSearchCriteria);

        final List<Route> routeList = routeRepository.findAll(routeSpecs);

        assertNotNull(routeList);
        final Route retrievedRoute = routeList.get(0);
        assertNotNull(retrievedRoute);
        assertEquals(route.getId(), retrievedRoute.getId());
        assertEquals(route.getDeparturePlace().getIcaoCode(),
                retrievedRoute.getDeparturePlace().getIcaoCode());
    }
}
