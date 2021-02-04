package com.finartz.airlinesystem.config;

import com.finartz.airlinesystem.dto.flight.FlightServiceInput;
import com.finartz.airlinesystem.dto.flight.FlightServiceOutput;
import com.finartz.airlinesystem.dto.route.RouteServiceInput;
import com.finartz.airlinesystem.dto.route.RouteServiceOutput;
import com.finartz.airlinesystem.dto.ticket.TicketServiceOutput;
import com.finartz.airlinesystem.entity.Flight;
import com.finartz.airlinesystem.entity.Route;
import com.finartz.airlinesystem.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Configuration
public class ModelMapperConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Route, RouteServiceOutput> routeToOutputMap = new PropertyMap<Route, RouteServiceOutput>() {
            protected void configure() {
                map().setDeparturePlace(source.getDeparturePlace().getId());
                map().setDestinationPlace(source.getDestinationPlace().getId());
            }
        };
        PropertyMap<RouteServiceInput, Route> inputMapToRoute = new PropertyMap<RouteServiceInput, Route>() {
            protected void configure() {
                map().getDeparturePlace().setId(source.getDeparturePlace());
                map().getDestinationPlace().setId(source.getDestinationPlace());
            }
        };
        PropertyMap<Flight, FlightServiceOutput> flightToOutputMap = new PropertyMap<Flight, FlightServiceOutput>() {
            protected void configure() {
                map().setDeparturePlace(source.getRoute().getDeparturePlace().getCity());
                map().setDestinationPlace(source.getRoute().getDestinationPlace().getCity());
                map().setAirline(source.getAirline().getName());
            }
        };
        PropertyMap<FlightServiceInput, Flight> inputMapToFlight = new PropertyMap<FlightServiceInput, Flight>() {
            protected void configure() {
                map().getAirline().setId(source.getAirline());
                map().getRoute().setId(source.getRoute());
            }
        };
        PropertyMap<Ticket, TicketServiceOutput> ticketToOutputMap = new PropertyMap<Ticket, TicketServiceOutput>() {
            protected void configure() {
                map().setDeparturePlace(
                        source.getFlight().getRoute().getDeparturePlace().getName());
                map().setDestinationPlace(
                        source.getFlight().getRoute().getDestinationPlace().getName());
            }
        };
        modelMapper.addMappings(routeToOutputMap);
        modelMapper.addMappings(flightToOutputMap);
        modelMapper.addMappings(inputMapToRoute);
        modelMapper.addMappings(inputMapToFlight);
        modelMapper.addMappings(ticketToOutputMap);
        return modelMapper;
    }
}