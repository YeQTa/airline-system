package com.finartz.airlinesystem.service.airport;

import com.finartz.airlinesystem.dto.airport.AirportServiceInput;
import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;
import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.repository.AirportRepository;
import com.finartz.airlinesystem.spec.airport.AirportSearchCriteria;
import com.finartz.airlinesystem.spec.airport.AirportSpecs;
import java.util.ArrayList;
import java.util.List;
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

    private ModelMapper modelMapper;
    private AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(ModelMapper modelMapper,
            AirportRepository airportRepository) {
        this.modelMapper = modelMapper;
        this.airportRepository = airportRepository;
    }

    @Override
    public AirportServiceOutput createAirport(AirportServiceInput airportServiceInput) {
        Airport airport = modelMapper.map(airportServiceInput, Airport.class);

        return modelMapper.map(airportRepository.save(airport), AirportServiceOutput.class);
    }

    @Override
    public List<AirportServiceOutput> searchAirport(AirportServiceInput airportServiceInput) {
        AirportSearchCriteria airportSearchCriteria = modelMapper
                .map(airportServiceInput, AirportSearchCriteria.class);
        Specification<Airport> airportSpecs = AirportSpecs
                .findAirportByCriterias(airportSearchCriteria);

        List<AirportServiceOutput> result = new ArrayList<>();
        airportRepository.findAll(airportSpecs)
                .forEach(x -> result.add(modelMapper.map(x, AirportServiceOutput.class)));
        return result;
    }

    @Override
    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionConstants.AIRPORT_NOT_FOUND));
    }
}
