package com.finartz.airlinesystem.utility;

import com.finartz.airlinesystem.entity.Airline;
import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.entity.Flight;
import com.finartz.airlinesystem.entity.Route;
import com.finartz.airlinesystem.entity.Ticket;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;


/**
 * @author : Yekta Anil AKSOY
 * @since : 29.01.2021
 **/
public final class EntityTestUtility {

    public final static LocalDateTime now = LocalDateTime.now();
    public final static UUID ticketCode = UUID.randomUUID();

    public static Airport getAirport() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCity("istanbul");
        airport.setIcaoCode("SAW");
        airport.setCreateDate(now);
        airport.setStatus(1);
        airport.setName("Sabiha Gökçen Havalimanı");
        return airport;
    }

    public static Airport getDestinationAirport() {
        Airport destinationPlace = new Airport();
        destinationPlace.setId(2L);
        destinationPlace.setStatus(1);
        destinationPlace.setCreateDate(now);
        destinationPlace.setIcaoCode("TZX");
        destinationPlace.setCity("trabzon");
        destinationPlace.setName("Trabzon Havalimanı");
        return destinationPlace;
    }


    public static Route getRoute() {
        Route route = new Route();
        route.setId(1L);
        route.setDeparturePlace(getAirport());
        route.setDestinationPlace(getDestinationAirport());
        route.setCreateDate(now);
        route.setStatus(1);
        route.setFlyingTime(LocalTime.of(1, 30));
        return route;
    }

    public static Flight getFlight() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setCapasity(150);
        flight.setPrice(new BigDecimal(200));
        flight.setFlightDate(now);
        flight.setRoute(getRoute());
        flight.setStatus(1);
        flight.setCreateDate(now);
        flight.setAirline(getAirline());
        return flight;
    }

    public static Airline getAirline() {
        Airline airline = new Airline();
        airline.setId(1L);
        airline.setName("Pegasus Hava Yolları");
        airline.setStatus(1);
        airline.setCreateDate(now);
        return airline;
    }

    public static Ticket getTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setAmount(new BigDecimal(220));
        ticket.setTicketCode(ticketCode);
        ticket.setCreditCard("1234567890123456");
        ticket.setTicketStatus(TicketStatus.SOLD);
        ticket.setFlight(getFlight());
        ticket.setStatus(1);
        ticket.setTicketStatus(TicketStatus.SOLD);
        ticket.setCreateDate(now);
        return ticket;
    }
}
