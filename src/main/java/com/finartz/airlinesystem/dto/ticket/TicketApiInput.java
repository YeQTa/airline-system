package com.finartz.airlinesystem.dto.ticket;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 3.02.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketApiInput {

    private Long id;
    private String creditCard;
    private UUID ticketCode;
    private Long flight;
}
