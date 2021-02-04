package com.finartz.airlinesystem.exception;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

public final class ExceptionConstants {


    private ExceptionConstants() {
    }

    public static final String TICKET_NOT_FOUND = "Ticket is not found.";
    public static final String AIRPORT_NOT_FOUND = "Airport is not found.";
    public static final String AIRLINE_NOT_FOUND = "Airline is not found.";
    public static final String FLIGHT_NOT_FOUND = "Flight is not found.";
    public static final String ROUTE_NOT_FOUND = "Route is not found.";
    public static final String CAPACITY_FULL = "Since the capacity of the flight is full, you cannot purchase a ticket";
    public static final String DEPARTURE_DESTINATION_MATCH = "Departure place and destination place cannot be equal to each other";
    public static final String CC_LENGTH_INVALID = "Invalid credit card, it must be a sequence of integers with size 16.";

}
