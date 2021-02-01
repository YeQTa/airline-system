package com.finartz.airlinesystem.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.finartz.airlinesystem.utility.EntityTestUtility;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/

class TicketTest {

    @Test
    void testTicketCreate() {
        final Ticket ticket = new Ticket();
        assertNotNull(ticket);
    }

    @Test
    void testTicketSettersAndGetters() {
        Ticket ticket = EntityTestUtility.getTicket();

        assertEquals(1L, ticket.getId());
        assertEquals("1234567890123456", ticket.getCreditCard());
        assertEquals(0, new BigDecimal(220).compareTo(ticket.getAmount()));
        assertEquals(EntityTestUtility.ticketCode, ticket.getTicketCode());
    }
}
