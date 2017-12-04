package com.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
         * 如果CustomerServiceImpl不是CustomerServiceInterface的实现的话，可以使用本条语句
         * CustomerServiceImpl customer = (CustomerServiceImpl) context.getBean("customerService");
         */
        CustomerServiceInterface customer = (CustomerServiceInterface) context.getBean("customerService");

        customer.save();
        customer.update();
        customer.transfer(666D);
        // customer.error();
    }
}
