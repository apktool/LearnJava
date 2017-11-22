package com.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        HelloApplication obj = (HelloApplication) context.getBean("helloBean");
        obj.printHello();
        ((ClassPathXmlApplicationContext)context).close();
    }
}
