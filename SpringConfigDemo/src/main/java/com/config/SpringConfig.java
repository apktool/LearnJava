package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.config"})
@PropertySource(value = "classpath:jdbc.properties")
public class SpringConfig {
    public SpringConfig() {
        System.out.println("Spring environments is initialized successfully.");
    }
}
