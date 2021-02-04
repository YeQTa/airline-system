package com.finartz.airlinesystem.service.ticket;

import com.finartz.airlinesystem.dto.ticket.TicketPriceDTO;
import com.finartz.airlinesystem.dto.ticket.TicketServiceInput;
import com.finartz.airlinesystem.dto.ticket.TicketServiceOutput;
import com.finartz.airlinesystem.entity.Flight;
import com.finartz.airlinesystem.entity.Ticket;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.exception.FullCapacityException;
import com.finartz.airlinesystem.repository.TicketRepository;
import com.finartz.airlinesystem.service.flight.FlightService;
import com.finartz.airlinesystem.utility.TicketStatus;
import com.finartz.airlinesystem.utility.Utils;
import java.math.BigDecimal;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/

@Service
public class TicketServiceImpl implements TicketService {

    private ModelMapper modelMapper;
    private TicketRepository ticketRepository;
    private FlightService flightService;

    @Autowired
    public TicketServiceImpl(ModelMapper modelMapper, TicketRepository ticketRepository,
            FlightService flightService) {
        this.modelMapper = modelMapper;
        this.ticketRepository = ticketRepository;
        this.flightService = flightService;
    }

    @Override
    public TicketServiceOutput buyTicket(TicketServiceInput ticketServiceInput) {
        ticketServiceInput.setCreditCard(Utils.maskCardNumber(ticketServiceInput.getCreditCard(),
                Utils.DEFAULT_CC_FORMAT)); // Mask Credit Card
        final Ticket ticket = modelMapper.map(ticketServiceInput, Ticket.class);
        boolean isCapacityFull = ticketRepository
                .isCapacityFull(ticketServiceInput.getFlight(), TicketStatus.SOLD);
        if (!isCapacityFull) {
            ticket.setTicketCode(UUID.randomUUID());
            ticket.setTicketStatus(TicketStatus.SOLD);
            ticket.setAmount(calculatePrice(ticketServiceInput.getFlight()));
            final Flight flight = flightService.findById(ticketServiceInput.getFlight());
            ticket.setFlight(flight);
            final Ticket savedTicket = ticketRepository.save(ticket);
            return modelMapper.map(savedTicket, TicketServiceOutput.class);
        }
        throw new FullCapacityException(ExceptionConstants.CAPACITY_FULL);
    }

    @Override
    public TicketServiceOutput searchTicket(String ticketCode) {
        final Ticket ticket = ticketRepository
                .findTicketByTicketCode(UUID.fromString(ticketCode))
                .orElseThrow(() -> new DataNotFoundException(ExceptionConstants.TICKET_NOT_FOUND));
        return modelMapper.map(ticket, TicketServiceOutput.class);
    }

    @Override
    public TicketServiceOutput returnTicket(String ticketCode) {
        Ticket ticket = ticketRepository
                .findTicketByTicketCode(UUID.fromString(ticketCode))
                .orElseThrow(() -> new DataNotFoundException(ExceptionConstants.TICKET_NOT_FOUND));
        ticket.setTicketStatus(TicketStatus.RETURNED);

        return modelMapper.map(ticketRepository.save(ticket), TicketServiceOutput.class);
    }

    @Override
    public BigDecimal calculatePrice(Long flightId) {
        final TicketPriceDTO ticketPriceDTO = ticketRepository
                .findPrizeInfo(flightId, TicketStatus.SOLD);
        final Integer capacity = ticketPriceDTO.getCapacity();
        final Long sold = ticketPriceDTO.getSold();
        final BigDecimal price = ticketPriceDTO.getPrice();

        final Long numOfTenPercentage = (sold * 10) / (capacity);

        return price.add(price
                .multiply(new BigDecimal(numOfTenPercentage * 10).divide(new BigDecimal("100"))));
    }
}
