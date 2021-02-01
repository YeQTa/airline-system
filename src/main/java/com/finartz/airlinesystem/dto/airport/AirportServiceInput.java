package com.finartz.airlinesystem.dto.airport;

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
public class AirportServiceInput {

    private Long id;
    private String name;
    private String icaoCode;
    private String city;
}
