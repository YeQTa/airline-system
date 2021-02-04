package com.finartz.airlinesystem.service.airline;

import com.finartz.airlinesystem.dto.airline.AirlineServiceInput;
import com.finartz.airlinesystem.dto.airline.AirlineServiceOutput;
import com.finartz.airlinesystem.entity.Airline;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.repository.AirlineRepository;
import com.finartz.airlinesystem.spec.airline.AirlineSearchCriteria;
import com.finartz.airlinesystem.spec.airline.AirlineSpecs;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/

@Service
public class AirlineServiceImpl implements AirlineService {

    private ModelMapper modelMapper;
    private AirlineRepository airlineRepository;

    @Autowired
    public AirlineServiceImpl(ModelMapper modelMapper,
            AirlineRepository airlineRepository) {
        this.modelMapper = modelMapper;
        this.airlineRepository = airlineRepository;
    }

    @Override
    public AirlineServiceOutput createAirline(AirlineServiceInput airlineServiceInput) {
        airlineServiceInput.setId(null);

        final Airline savedAirline = airlineRepository
                .save(modelMapper.map(airlineServiceInput, Airline.class));

        return modelMapper.map(savedAirline, AirlineServiceOutput.class);
    }

    @Override
    public List<AirlineServiceOutput> searchAirline(AirlineServiceInput airlineServiceInput) {
        AirlineSearchCriteria airlineSearchCriteria = modelMapper
                .map(airlineServiceInput, AirlineSearchCriteria.class);
        Specification<Airline> airlineSpecs = AirlineSpecs
                .findAirlineByCriterias(airlineSearchCriteria);

        List<AirlineServiceOutput> result = new ArrayList<>();
        airlineRepository.findAll(airlineSpecs)
                .forEach(x -> result.add(modelMapper.map(x, AirlineServiceOutput.class)));
        return result;
    }

    @Override
    public Airline findAirlineById(Long id) {
        return airlineRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                ExceptionConstants.AIRLINE_NOT_FOUND));
    }
}
