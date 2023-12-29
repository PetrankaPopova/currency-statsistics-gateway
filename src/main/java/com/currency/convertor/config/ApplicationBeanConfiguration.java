package com.currency.convertor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/**
 * The ApplicationBeanConfiguration class is a Spring configuration class annotated with @Configuration.
 * Its purpose is to define and configure application-specific beans for use in the Spring context.
 *
 * In this specific configuration, a RestTemplate bean is defined using the @Bean annotation. The RestTemplate
 * is a Spring class that simplifies making HTTP requests, and having it as a bean allows for easy injection
 * and reuse throughout the application.
 */

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
