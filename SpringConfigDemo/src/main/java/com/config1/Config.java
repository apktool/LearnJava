package com.config1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = Company.class)
@Configuration
public class Config {

    @Bean
    public Address getAddress() {
        return new Address("Height street", 999);
    }
}
