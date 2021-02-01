package com.finartz.airlinesystem.service;

import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;
import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.repository.AirportRepository;
import com.finartz.airlinesystem.spec.airport.AirportSearchCriteria;
import com.finartz.airlinesystem.spec.airport.AirportSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Service
public class AirportServiceImpl implements AirportService {

    private ModelMapper mapper;
    private AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(ModelMapper mapper,
            AirportRepository airportRepository) {
        this.mapper = mapper;
        this.airportRepository = airportRepository;
    }

    @Override
    public AirportServiceOutput createAirport(AirportServiceInput airportServiceInput) {
        Airport airport = mapper.map(airportServiceInput, Airport.class);

        return mapper.map(airportRepository.save(airport), AirportServiceOutput.class);
    }

    @Override
    public AirportServiceOutput getAirport(AirportServiceInput airportServiceInput) {
        AirportSearchCriteria airportSearchCriteria = mapper
                .map(airportServiceInput, AirportSearchCriteria.class);
        Specification<Airport> airportSpecs = AirportSpecs
                .findAirportByCriterias(airportSearchCriteria);

        final Airport airport = airportRepository.findOne(airportSpecs)
                .orElseThrow(() -> new DataNotFoundException(ExceptionConstants.AIRPORT_NOT_FOUND));
        
        return mapper.map(airport, AirportServiceOutput.class);
    }
}
