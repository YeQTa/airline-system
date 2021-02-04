package com.finartz.airlinesystem.dto.route;

import java.time.LocalTime;
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
public class RouteApiInput {

    private Long id;
    private Long departurePlace;
    private Long destinationPlace;
    private String status;
    private LocalTime flyingTime;
}
