package com.config1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Company company = ctx.getBean(Company.class);
        System.out.println(company.getAddress().getNumber());
        System.out.println(company.getAddress().getStreet());
    }
}
