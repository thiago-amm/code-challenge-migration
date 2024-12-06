package com.example.dummyjson.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RestTemplateConfigTest {

    @InjectMocks
    RestTemplateConfig restTemplateConfig;

    @Test
    void testRestTemplateConfig(){
        assertNotNull(this.restTemplateConfig.restTemplate());
    }
}
