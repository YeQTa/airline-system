package com.finartz.airlinesystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.finartz.airlinesystem.config.ModelMapperConfig;
import com.finartz.airlinesystem.dto.airline.AirlineServiceOutput;
import com.finartz.airlinesystem.entity.Airline;
import com.finartz.airlinesystem.repository.AirlineRepository;
import com.finartz.airlinesystem.service.airline.AirlineService;
import com.finartz.airlinesystem.service.airline.AirlineServiceImpl;
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
 * @since : 2.02.2021
 **/

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ModelMapperConfig.class
)
class AirlineServiceTest {

    @SpyBean
    private ModelMapper modelMapper;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private final AirlineService airlineService = new AirlineServiceImpl(modelMapper,
            airlineRepository);

    @Test
    void givenAirline_whenSave_thenShouldReturnAirlineServiceOutput() {
        final Airline airline = EntityTestUtility.getAirline();
        when(airlineRepository.save(any(Airline.class))).thenReturn(airline);

        AirlineServiceOutput airlineServiceOutput = airlineService
                .createAirline(ServiceTestUtility.getAirlineServiceInput());

        assertEquals(airline.getId(), airlineServiceOutput.getId());
        assertEquals(airline.getStatus(), airlineServiceOutput.getStatus());
        assertEquals(airline.getName(), airlineServiceOutput.getName());
    }

    @Test
    void givenAirline_whenSearch_thenShouldReturnAirlineServiceOutputList() {
        final Airline airline = EntityTestUtility.getAirline();
        when(airlineRepository.findAll(any(Specification.class)))
                .thenReturn(Collections.singletonList(airline));

        List<AirlineServiceOutput> airlineServiceOutputList = airlineService.searchAirline(
                ServiceTestUtility.getAirlineServiceInput());

        assertNotNull(airlineServiceOutputList);
        assertEquals(1, airlineServiceOutputList.size());
        final AirlineServiceOutput retrievedAirlineServiceOutput = airlineServiceOutputList.get(0);
        assertEquals(airline.getId(), retrievedAirlineServiceOutput.getId());
        assertEquals(airline.getName(), retrievedAirlineServiceOutput.getName());
        assertEquals(airline.getStatus(), retrievedAirlineServiceOutput.getStatus());
    }

}
