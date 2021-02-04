package com.finartz.airlinesystem.repository;

import com.finartz.airlinesystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 29.01.2021
 **/
public interface FlightRepository extends JpaRepository<Flight, Long>,
        JpaSpecificationExecutor<Flight> {

}
