package com.finartz.airlinesystem.repository;

import com.finartz.airlinesystem.dto.ticket.TicketPriceDTO;
import com.finartz.airlinesystem.entity.Ticket;
import com.finartz.airlinesystem.utility.TicketStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/
public interface TicketRepository extends JpaRepository<Ticket, Long>,
        JpaSpecificationExecutor<Ticket> {

    Optional<Ticket> findTicketByTicketCode(UUID ticketCode);

    @Query("select new com.finartz.airlinesystem.dto.ticket.TicketPriceDTO((select count(t) from Ticket t where t.flight.id = :flightId and t.ticketStatus = :ticketStatus),f.capacity,f.price) from Flight f where f.id = :flightId")
    TicketPriceDTO findPriceInfo(@Param("flightId") Long flightId,
            @Param("ticketStatus") TicketStatus ticketStatus);

    @Query("SELECT CASE WHEN COUNT(t) >= f.capacity THEN true ELSE false END FROM Ticket t inner join Flight f on t.flight.id = f.id where f.id = :flightId and t.ticketStatus = :ticketStatus")
    Boolean isCapacityFull(@Param("flightId") Long flightId,
            @Param("ticketStatus") TicketStatus ticketStatus);
}
