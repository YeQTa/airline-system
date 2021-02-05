package com.finartz.airlinesystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.finartz.airlinesystem.config.ModelMapperConfig;
import com.finartz.airlinesystem.dto.ticket.TicketPriceDTO;
import com.finartz.airlinesystem.dto.ticket.TicketServiceOutput;
import com.finartz.airlinesystem.entity.Ticket;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.repository.TicketRepository;
import com.finartz.airlinesystem.service.flight.FlightService;
import com.finartz.airlinesystem.service.ticket.TicketService;
import com.finartz.airlinesystem.service.ticket.TicketServiceImpl;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import com.finartz.airlinesystem.utility.ServiceTestUtility;
import com.finartz.airlinesystem.utility.TicketStatus;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 * @author : Yekta Anil AKSOY
 * @since : 4.02.2021
 **/

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ModelMapperConfig.class
)
class TicketServiceTest {

    @SpyBean
    private ModelMapper modelMapper;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private final TicketService ticketService = new TicketServiceImpl(modelMapper, ticketRepository,
            flightService);

    @Test
    void givenTicketServiceInput_whenBuyTicket_thenShouldReturnTicketServiceOutput() {
        final Ticket ticket = EntityTestUtility.getTicket();
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(ticketRepository.findPriceInfo(anyLong(), any(TicketStatus.class)))
                .thenReturn(new TicketPriceDTO(0L, ticket.getFlight().getCapacity(),
                        ticket.getFlight().getPrice()));
        TicketServiceOutput ticketServiceOutput = ticketService
                .buyTicket(ServiceTestUtility.getTicketServiceInput());

        assertNotNull(ticketServiceOutput);
        assertEquals(ticket.getTicketCode(), ticketServiceOutput.getTicketCode());
        assertEquals(0, ticket.getAmount().compareTo(ticketServiceOutput.getAmount()));
    }

    @Test
    void givenTicketCode_whenSearchTicket_thenShouldReturnTicketServiceOutput() {
        final Ticket ticket = EntityTestUtility.getTicket();
        when(ticketRepository.findTicketByTicketCode(any(UUID.class)))
                .thenReturn(Optional.of(ticket));

        final TicketServiceOutput ticketServiceOutput = ticketService
                .searchTicket(ticket.getTicketCode().toString());

        assertNotNull(ticketServiceOutput);
        assertEquals(ticket.getId(), ticketServiceOutput.getId());
        assertEquals(0, ticket.getAmount().compareTo(ticketServiceOutput.getAmount()));
        assertEquals(ticket.getTicketStatus(), ticketServiceOutput.getTicketStatus());
    }

    @Test
    void givenTicketCode_whenSearchTicket_thenShouldThrowDataNotFoundException() {
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            ticketService.searchTicket(EntityTestUtility.getTicket().getTicketCode().toString());
        });
    }

    @Test
    void givenTicketCode_whenReturnTicket_thenShouldReturnTicket() {
        final Ticket ticket = EntityTestUtility.getTicket();
        when(ticketRepository.findTicketByTicketCode(any(UUID.class)))
                .thenReturn(Optional.of(ticket));
        Ticket returnedTicket = EntityTestUtility.getTicket();
        returnedTicket.setTicketStatus(TicketStatus.RETURNED);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(returnedTicket);

        final TicketServiceOutput ticketServiceOutput = ticketService
                .returnTicket(ticket.getTicketCode().toString());

        assertNotNull(ticketServiceOutput);
        assertEquals(TicketStatus.RETURNED, ticketServiceOutput.getTicketStatus());
        assertEquals(ticket.getId(), ticketServiceOutput.getId());
    }
}
