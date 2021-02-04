package com.finartz.airlinesystem.service.route;

import com.finartz.airlinesystem.dto.route.RouteServiceInput;
import com.finartz.airlinesystem.dto.route.RouteServiceOutput;
import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.entity.Route;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.repository.RouteRepository;
import com.finartz.airlinesystem.service.airport.AirportService;
import com.finartz.airlinesystem.spec.route.RouteSearchCriteria;
import com.finartz.airlinesystem.spec.route.RouteSpecs;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Service
public class RouteServiceImpl implements RouteService {

    private ModelMapper modelMapper;
    private RouteRepository routeRepository;
    private AirportService airportService;

    @Autowired
    public RouteServiceImpl(ModelMapper modelMapper,
            RouteRepository routeRepository,
            AirportService airportService) {
        this.modelMapper = modelMapper;
        this.routeRepository = routeRepository;
        this.airportService = airportService;
    }

    @Override
    public RouteServiceOutput createRoute(RouteServiceInput routeServiceInput) {
        routeServiceInput.setId(null);
        if (routeServiceInput.getDeparturePlace().equals(routeServiceInput.getDestinationPlace())) {
            throw new UnsupportedOperationException(ExceptionConstants.DEPARTURE_DESTINATION_MATCH);
        }
        final Airport departureAirport = airportService
                .getAirportById(routeServiceInput.getDeparturePlace());
        final Airport destinationAirport = airportService
                .getAirportById(routeServiceInput.getDestinationPlace());
        Route route = modelMapper.map(routeServiceInput, Route.class);
        route.setDeparturePlace(departureAirport);
        route.setDestinationPlace(destinationAirport);
        final Route savedRoute = routeRepository.save(route);

        return modelMapper.map(savedRoute, RouteServiceOutput.class);
    }

    @Override
    public List<RouteServiceOutput> searchRoute(RouteServiceInput routeServiceInput) {
        RouteSearchCriteria routeSearchCriteria = modelMapper
                .map(routeServiceInput, RouteSearchCriteria.class);
        Specification<Route> routeSpecs = RouteSpecs.findRouteByCriterias(routeSearchCriteria);

        List<RouteServiceOutput> result = new ArrayList<>();
        routeRepository.findAll(routeSpecs)
                .forEach(x -> result
                        .add(modelMapper.map(x, RouteServiceOutput.class)));

        return result;
    }

    @Override
    public Route findRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionConstants.ROUTE_NOT_FOUND));
    }
}
