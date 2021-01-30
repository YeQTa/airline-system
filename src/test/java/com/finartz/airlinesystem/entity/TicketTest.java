package com.finartz.airlinesystem.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    /*@Test
    void testTicketSettersAndGetters() {
        Ticket ticket = EntityTestUtility.getTicket();
    }*/
}
