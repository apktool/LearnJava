package com.springel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        CustomDaoImpl customerDao = (CustomDaoImpl) context.getBean("customerDao");
        System.out.println(customerDao);
    }
}
