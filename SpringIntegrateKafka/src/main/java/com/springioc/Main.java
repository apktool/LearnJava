package com.springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author apktool
 * @date 2018-08-31 10:07
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) applicationContext.getBean("user");

        String name = user.getName();
        Integer age = user.getAge();
        System.out.println(name + " " + age);
    }
}
