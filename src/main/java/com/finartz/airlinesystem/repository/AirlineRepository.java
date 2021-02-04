package com.finartz.airlinesystem.repository;

import com.finartz.airlinesystem.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 30.01.2021
 **/
public interface AirlineRepository extends JpaRepository<Airline, Long>,
        JpaSpecificationExecutor<Airline> {

}
