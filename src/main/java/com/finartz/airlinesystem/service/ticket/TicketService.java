package com.finartz.airlinesystem.service.ticket;

import com.finartz.airlinesystem.dto.ticket.TicketServiceInput;
import com.finartz.airlinesystem.dto.ticket.TicketServiceOutput;
import java.math.BigDecimal;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/

public interface TicketService {

    TicketServiceOutput buyTicket(TicketServiceInput ticketServiceInput);

    TicketServiceOutput searchTicket(String ticketCode);

    TicketServiceOutput returnTicket(String ticketCode);

    BigDecimal calculatePrice(Long flightId);
}
