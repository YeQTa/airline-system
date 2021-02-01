package com.finartz.airlinesystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Yekta Anil AKSOY
 * @since : 1.02.2021
 **/

@Configuration
public class ModelMapperConfig {

    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
