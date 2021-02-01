package com.finartz.airlinesystem.spec.ticket;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketSearchCriteria {

    private UUID ticketCode;
    private Integer status;
}
