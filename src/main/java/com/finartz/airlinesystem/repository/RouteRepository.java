package com.finartz.airlinesystem.repository;

import com.finartz.airlinesystem.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 29.01.2021
 **/

public interface RouteRepository extends JpaRepository<Route, Long>,
        JpaSpecificationExecutor<Route> {

}
