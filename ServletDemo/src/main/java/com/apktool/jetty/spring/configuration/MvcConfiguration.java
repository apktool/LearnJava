package com.apktool.jetty.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author apktool
 * @package com.apktool.jetty.spring.configuration
 * @class MvcConfiguration
 * @description TODO
 * @date 2020-08-30 22:58
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.apktool.jetty.spring.controller"})
public class MvcConfiguration implements WebMvcConfigurer {
}
