package com.finartz.airlinesystem.spec.flight;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 29.01.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchCriteria {

    private Long id;
    private Integer status;
    private LocalDateTime flightDate;
    private String departurePlace;
    private String destinationPlace;
}
