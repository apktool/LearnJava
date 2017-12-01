package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.config"})
public class SpringConfig {
    public SpringConfig() {
        System.out.println("Spring environments is initialized successfully.");
    }
}
