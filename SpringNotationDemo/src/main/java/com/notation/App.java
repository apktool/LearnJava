package com.notation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
        CustomerDaoImpl customerDao = (CustomerDaoImpl) context.getBean("customDao");
        customerDao.save();
        System.out.println(customerDao);
        */
        CustomerServiceImpl customerService = (CustomerServiceImpl) context.getBean("customerService");
        customerService.save();
    }
}
