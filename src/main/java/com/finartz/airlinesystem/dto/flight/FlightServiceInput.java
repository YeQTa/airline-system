package com.finartz.airlinesystem.dto.flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightServiceInput {

    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime flightDate;
    private Long route;
    private Long airline;
    private Integer capacity;
    private BigDecimal price;
}
