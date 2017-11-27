package com.custome;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        CustomServiceImpl customService = (CustomServiceImpl) context.getBean("customService");
        customService.save();
    }
}
