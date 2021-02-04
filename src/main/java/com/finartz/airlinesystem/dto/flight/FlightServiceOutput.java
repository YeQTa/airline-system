package com.finartz.airlinesystem.dto.flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightServiceOutput {

    private Long id;
    private Integer status;
    private LocalDateTime flightDate;
    private String departurePlace;
    private String destinationPlace;
    private String airline;
    private LocalDateTime estimatedArrivalTime;
    private BigDecimal price;
    private Integer capacity;
}
