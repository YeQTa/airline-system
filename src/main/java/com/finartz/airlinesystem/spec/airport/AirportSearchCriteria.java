package com.finartz.airlinesystem.spec.airport;

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
public class AirportSearchCriteria {

    private Long id;
    private Integer status;
    private String icaoCode;
    private String city;
    private String name;
}
