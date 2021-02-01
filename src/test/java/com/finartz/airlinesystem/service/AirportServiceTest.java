package com.finartz.airlinesystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;
import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.repository.AirportRepository;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import com.finartz.airlinesystem.utility.ServiceTestUtility;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService = new AirportServiceImpl(modelMapper, airportRepository);

    @Test
    void givenAirport_whenCreate_thenShouldReturnAirportServiceOutput() {
        final Airport airport = EntityTestUtility.getAirport();
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        AirportServiceOutput airportServiceOutput = airportService
                .createAirport(ServiceTestUtility.getAirportServiceInput());

        assertEquals(airport.getId(), airportServiceOutput.getId());
        assertEquals(airport.getIcaoCode(), airportServiceOutput.getIcaoCode());
        assertEquals(airport.getCity(), airportServiceOutput.getCity());
        assertEquals(airport.getName(), airportServiceOutput.getName());
    }

    @Test
    void givenCriteria_whenGet_thenShouldReturnAirportServiceOutput() {
        final Airport airport = EntityTestUtility.getAirport();
        when(airportRepository.findOne(any(Specification.class)))
                .thenReturn(Optional.of(airport));

        AirportServiceOutput airportServiceOutput = airportService
                .getAirport(ServiceTestUtility.getAirportServiceInput());

        assertEquals(airport.getId(), airportServiceOutput.getId());
        assertEquals(airport.getIcaoCode(), airportServiceOutput.getIcaoCode());
        assertEquals(airport.getCity(), airportServiceOutput.getCity());
        assertEquals(airport.getName(), airportServiceOutput.getName());
    }
}
