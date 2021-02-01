package com.finartz.airlinesystem.repository;

import com.finartz.airlinesystem.entity.Ticket;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/
public interface TicketRepository extends JpaRepository<Ticket, Long>,
        JpaSpecificationExecutor<Ticket> {

    Ticket findTicketByTicketCode(UUID ticketCode);
}
