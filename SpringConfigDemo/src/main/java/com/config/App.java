package com.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CustomerDaoImpl customerDao = (CustomerDaoImpl) context.getBean("customerDao");
        System.out.println(customerDao);
    }
}
