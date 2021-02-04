package com.finartz.airlinesystem.service.flight;

import com.finartz.airlinesystem.dto.flight.FlightServiceInput;
import com.finartz.airlinesystem.dto.flight.FlightServiceOutput;
import com.finartz.airlinesystem.entity.Airline;
import com.finartz.airlinesystem.entity.Flight;
import com.finartz.airlinesystem.entity.Route;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.repository.FlightRepository;
import com.finartz.airlinesystem.service.airline.AirlineService;
import com.finartz.airlinesystem.service.route.RouteService;
import com.finartz.airlinesystem.service.ticket.TicketService;
import com.finartz.airlinesystem.spec.flight.FlightSearchCriteria;
import com.finartz.airlinesystem.spec.flight.FlightSpecs;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Service
public class FlightServiceImpl implements FlightService {

    private ModelMapper modelMapper;
    private FlightRepository flightRepository;
    private AirlineService airlineService;
    private RouteService routeService;
    private TicketService ticketService;

    @Autowired
    public FlightServiceImpl(ModelMapper modelMapper,
            FlightRepository flightRepository,
            AirlineService airlineService,
            RouteService routeService,
            @Lazy TicketService ticketService) {
        this.modelMapper = modelMapper;
        this.flightRepository = flightRepository;
        this.airlineService = airlineService;
        this.routeService = routeService;
        this.ticketService = ticketService;
    }


    @Override
    public FlightServiceOutput createFlight(FlightServiceInput flightServiceInput) {
        flightServiceInput.setId(null);

        final Airline airline = airlineService.findAirlineById(flightServiceInput.getAirline());
        final Route route = routeService.findRouteById(flightServiceInput.getRoute());

        Flight flight = modelMapper.map(flightServiceInput, Flight.class);
        flight.setRoute(route);
        flight.setAirline(airline);
        final Flight savedFlight = flightRepository.save(flight);
        airline.getFlights().add(savedFlight);
        route.getFlights().add(savedFlight);
        return modelMapper.map(savedFlight, FlightServiceOutput.class);
    }

    @Override
    public List<FlightServiceOutput> searchFlight(FlightServiceInput flightServiceInput) {
        FlightSearchCriteria flightSearchCriteria = modelMapper
                .map(flightServiceInput, FlightSearchCriteria.class);
        Specification<Flight> flightSpecs = FlightSpecs.findFlightByCriterias(flightSearchCriteria);

        List<FlightServiceOutput> result = new ArrayList<>();
        final BigDecimal calculatedPrice = ticketService.calculatePrice(flightServiceInput.getId());
        flightRepository.findAll(flightSpecs)
                .forEach(x -> {
                            x.setPrice(calculatedPrice);
                            result.add(modelMapper.map(x, FlightServiceOutput.class));
                        }
                );
        return result;
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                ExceptionConstants.FLIGHT_NOT_FOUND));
    }
}
