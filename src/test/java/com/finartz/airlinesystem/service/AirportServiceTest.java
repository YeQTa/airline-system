package com.finartz.airlinesystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.finartz.airlinesystem.config.ModelMapperConfig;
import com.finartz.airlinesystem.dto.airport.AirportServiceOutput;
import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.repository.AirportRepository;
import com.finartz.airlinesystem.service.airport.AirportService;
import com.finartz.airlinesystem.service.airport.AirportServiceImpl;
import com.finartz.airlinesystem.utility.EntityTestUtility;
import com.finartz.airlinesystem.utility.ServiceTestUtility;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ModelMapperConfig.class
)
class AirportServiceTest {

    @SpyBean
    private ModelMapper modelMapper;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private final AirportService airportService = new AirportServiceImpl(modelMapper,
            airportRepository);

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
    void givenCriteria_whenSearch_thenShouldReturnAirportServiceOutputList() {
        final Airport airport = EntityTestUtility.getAirport();
        when(airportRepository
                .findAll(any(Specification.class))).thenReturn(Collections.singletonList(airport));

        List<AirportServiceOutput> airportServiceOutputList = airportService
                .searchAirport(ServiceTestUtility.getAirportServiceInput());

        assertNotNull(airportServiceOutputList);
        assertEquals(1, airportServiceOutputList.size());
        final AirportServiceOutput retrievedAirportServiceOutput = airportServiceOutputList.get(0);
        assertEquals(airport.getId(), retrievedAirportServiceOutput.getId());
        assertEquals(airport.getIcaoCode(), retrievedAirportServiceOutput.getIcaoCode());
        assertEquals(airport.getCity(), retrievedAirportServiceOutput.getCity());
        assertEquals(airport.getName(), retrievedAirportServiceOutput.getName());
    }
}
