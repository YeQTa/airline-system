package com.finartz.airlinesystem.dto.airline;

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
public class AirlineServiceOutput {

    private Long id;
    private Integer status;
    private String name;
}
