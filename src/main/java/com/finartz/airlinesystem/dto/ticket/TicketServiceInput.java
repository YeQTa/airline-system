package com.finartz.airlinesystem.dto.ticket;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketServiceInput {

    private Long id;
    private String creditCard;
    private UUID ticketCode;
    private Long flight;
}
