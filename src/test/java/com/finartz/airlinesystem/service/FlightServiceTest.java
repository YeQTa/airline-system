package com.finartz.airlinesystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.finartz.airlinesystem.config.ModelMapperConfig;
import com.finartz.airlinesystem.dto.flight.FlightServiceOutput;
import com.finartz.airlinesystem.entity.Flight;
import com.finartz.airlinesystem.repository.FlightRepository;
import com.finartz.airlinesystem.service.airline.AirlineService;
import com.finartz.airlinesystem.service.flight.FlightService;
import com.finartz.airlinesystem.service.flight.FlightServiceImpl;
import com.finartz.airlinesystem.service.route.RouteService;
import com.finartz.airlinesystem.service.ticket.TicketService;
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
class FlightServiceTest {

    @SpyBean
    private ModelMapper modelMapper;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private RouteService routeService;

    @Mock
    private TicketService ticketService;

    @Mock
    private AirlineService airlineService;
    @InjectMocks
    private FlightService flightService = new FlightServiceImpl(modelMapper, flightRepository,
            airlineService, routeService, ticketService);

    @Test
    void givenFlight_whenCreate_thenShouldReturnFlightServiceOutput() {
        final Flight flight = EntityTestUtility.getFlight();
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        when(airlineService.findAirlineById(anyLong())).thenReturn(EntityTestUtility.getAirline());
        when(routeService.findRouteById(anyLong())).thenReturn(EntityTestUtility.getRoute());
        FlightServiceOutput flightServiceOutput = flightService
                .createFlight(ServiceTestUtility.getFlightServiceInput());

        assertEquals(flight.getId(), flightServiceOutput.getId());
        assertEquals(flight.getPrice(), flightServiceOutput.getPrice());
        assertEquals(flight.getAirline().getName(), flightServiceOutput.getAirline());
        assertEquals(flight.getStatus(), flightServiceOutput.getStatus());
    }

    @Test
    void givenCriteria_whenSearch_thenShouldReturnFlightServiceOutputList() {
        final Flight flight = EntityTestUtility.getFlight();
        when(flightRepository.findAll(any(Specification.class))).thenReturn(
                Collections.singletonList(flight));
        when(ticketService.calculatePrice(flight.getId())).thenReturn(flight.getPrice());
        List<FlightServiceOutput> flightServiceOutputList = flightService
                .searchFlight(ServiceTestUtility.getFlightServiceInput());

        assertNotNull(flightServiceOutputList);
        assertEquals(1, flightServiceOutputList.size());

        final FlightServiceOutput retrievedFlight = flightServiceOutputList.get(0);

        assertEquals(flight.getAirline().getName(), retrievedFlight.getAirline());
        assertEquals(flight.getId(), retrievedFlight.getId());
        assertEquals(flight.getRoute().getDestinationPlace().getCity(),
                retrievedFlight.getDestinationPlace());
        assertEquals(flight.getRoute().getDeparturePlace().getCity(),
                retrievedFlight.getDeparturePlace());
    }
}
