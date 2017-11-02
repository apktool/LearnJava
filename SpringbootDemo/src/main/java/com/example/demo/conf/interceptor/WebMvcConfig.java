package com.example.demo.conf.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new LoggingInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login");
    }
}

