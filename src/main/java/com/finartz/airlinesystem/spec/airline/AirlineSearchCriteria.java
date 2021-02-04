package com.finartz.airlinesystem.spec.airline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 30.01.2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineSearchCriteria {

    private Long id;
    private Integer status;
    private String name;
}
