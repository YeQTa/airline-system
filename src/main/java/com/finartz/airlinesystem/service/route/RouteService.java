package com.finartz.airlinesystem.service.route;

import com.finartz.airlinesystem.dto.route.RouteServiceInput;
import com.finartz.airlinesystem.dto.route.RouteServiceOutput;
import com.finartz.airlinesystem.entity.Route;
import java.util.List;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

public interface RouteService {

    RouteServiceOutput createRoute(RouteServiceInput routeServiceInput);

    List<RouteServiceOutput> searchRoute(RouteServiceInput routeServiceInput);

    Route findRouteById(Long id);
}
