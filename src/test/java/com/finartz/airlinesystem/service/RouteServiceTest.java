package com.finartz.airlinesystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.finartz.airlinesystem.config.ModelMapperConfig;
import com.finartz.airlinesystem.dto.route.RouteServiceOutput;
import com.finartz.airlinesystem.entity.Route;
import com.finartz.airlinesystem.repository.RouteRepository;
import com.finartz.airlinesystem.service.airport.AirportService;
import com.finartz.airlinesystem.service.route.RouteService;
import com.finartz.airlinesystem.service.route.RouteServiceImpl;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import com.finartz.airlinesystem.utility.ServiceTestUtility;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ModelMapperConfig.class
)
class RouteServiceTest {

    @SpyBean
    private ModelMapper modelMapper;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private AirportService airportService;

    @InjectMocks
    private final RouteService routeService = new RouteServiceImpl(modelMapper, routeRepository,
            airportService);

    @Test
    void givenRoute_whenCreate_thenShouldReturnRouteServiceOutput() {
        final Route route = EntityTestUtility.getRoute();
        when(routeRepository.save(any(Route.class))).thenReturn(route);
        when(airportService.getAirportById(1L)).thenReturn(EntityTestUtility.getAirport());
        when(airportService.getAirportById(2L))
                .thenReturn(EntityTestUtility.getDestinationAirport());
        RouteServiceOutput routeServiceOutput = routeService
                .createRoute(ServiceTestUtility.getRouteServiceInput());

        assertEquals(route.getId(), routeServiceOutput.getId());
        assertEquals(route.getDestinationPlace().getId(), routeServiceOutput.getDestinationPlace());
        assertEquals(route.getDeparturePlace().getId(), routeServiceOutput.getDeparturePlace());
        assertEquals(route.getFlyingTime().toString(),
                routeServiceOutput.getFlyingTime().toString());
    }

    @Test
    void givenCriteria_whenSearch_thenShouldReturnRouteServiceOutput() {
        final Route route = EntityTestUtility.getRoute();
        when(routeRepository.findAll(any(Specification.class))).thenReturn(
                Collections.singletonList(route));

        List<RouteServiceOutput> routeServiceOutputList = routeService
                .searchRoute(ServiceTestUtility.getRouteServiceInput());

        assertNotNull(routeServiceOutputList);
        assertEquals(1, routeServiceOutputList.size());
        final RouteServiceOutput routeServiceOutput = routeServiceOutputList.get(0);
        assertEquals(route.getId(), routeServiceOutput.getId());
        assertEquals(route.getFlyingTime().toString(),
                routeServiceOutput.getFlyingTime().toString());
        assertEquals(route.getDeparturePlace().getId(), routeServiceOutput.getDeparturePlace());
        assertEquals(route.getDestinationPlace().getId(), routeServiceOutput.getDestinationPlace());
    }
}
