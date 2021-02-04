package com.finartz.airlinesystem.dto.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class FlightApiInput {

    private Long id;
    private Long route;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime flightDate;
    private Long airline;
    private Integer capacity;
    private BigDecimal price;
}
