package com.finartz.airlinesystem.spec.route;

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
public class RouteSearchCriteria {

    private Long id;
    private Integer status;
    private String departureIcao;
    private String destinationIcao;
}
