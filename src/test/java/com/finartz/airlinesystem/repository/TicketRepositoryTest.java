package com.finartz.airlinesystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.dto.ticket.TicketPriceDTO;
import com.finartz.airlinesystem.entity.Ticket;
import com.finartz.airlinesystem.spec.ticket.TicketSearchCriteria;
import com.finartz.airlinesystem.spec.ticket.TicketSpecs;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import com.finartz.airlinesystem.utility.TicketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AirlineRepository airlineRepository;

    @BeforeEach
    void setUp() {
        airportRepository.save(EntityTestUtility.getAirport());
        airportRepository.save(EntityTestUtility.getDestinationAirport());
        routeRepository.save(EntityTestUtility.getRoute());
        airlineRepository.save(EntityTestUtility.getAirline());
        flightRepository.save(EntityTestUtility.getFlight());
    }

    @Test
    void givenTicket_whenSave_thenShouldReturnSavedEntity() {
        final Ticket ticket = EntityTestUtility.getTicket();
        final Ticket savedTicket = ticketRepository.save(ticket);

        assertEquals(ticket.getId(), savedTicket.getId());
    }

    @Test
    void givenCriterias_whenSearch_thenShouldReturnRelatedEntity() {
        final Ticket ticket = EntityTestUtility.getTicket();
        ticketRepository.save(ticket);

        TicketSearchCriteria ticketSearchCriteria = new TicketSearchCriteria();
        ticketSearchCriteria.setStatus(1);
        ticketSearchCriteria.setTicketCode(ticket.getTicketCode());

        Specification<Ticket> ticketSpecs = TicketSpecs.findTicketByCriterias(ticketSearchCriteria);

        final Ticket retrievedTicket = ticketRepository.findOne(ticketSpecs).get();

        assertNotNull(retrievedTicket);
        assertEquals(ticket.getId(), retrievedTicket.getId());
        assertEquals(ticket.getTicketCode(), retrievedTicket.getTicketCode());
    }

    @Test
    void givenFlightIdAndTicketStatus_whenFindPriceInfo_thenShouldReturnTicketPriceDTO() {
        TicketPriceDTO ticketPriceDTO = ticketRepository.findPriceInfo(1L, TicketStatus.SOLD);

        assertNotNull(ticketPriceDTO);
        assertEquals(0,
                EntityTestUtility.getFlight().getPrice().compareTo(ticketPriceDTO.getPrice()));
        assertEquals(EntityTestUtility.getFlight().getCapacity(), ticketPriceDTO.getCapacity());
        assertNotNull(ticketPriceDTO.getSold());
    }

    @Test
    void givenFlightIdAndTicketStatus_whenIsCapacityFull_thenShouldReturnFalse() {
        boolean isCapacityFull = ticketRepository.isCapacityFull(1L, TicketStatus.SOLD);

        assertFalse(isCapacityFull);
    }
}
