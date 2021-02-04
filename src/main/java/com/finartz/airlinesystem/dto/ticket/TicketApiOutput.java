package com.finartz.airlinesystem.dto.ticket;

import com.finartz.airlinesystem.utility.TicketStatus;
import java.math.BigDecimal;
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
public class TicketApiOutput {

    private Long id;
    private UUID ticketCode;
    private BigDecimal amount;
    private TicketStatus ticketStatus;
    private String departurePlace;
    private String destinationPlace;
}
