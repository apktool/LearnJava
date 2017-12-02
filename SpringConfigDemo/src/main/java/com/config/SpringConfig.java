package com.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.config"})
public class SpringConfig {
    @Bean(name="customerDaoa")
    public CustomerDaoInterface getCustomerDao() {
        return new CustomerDaoImpl();
    }

    public SpringConfig() {
        System.out.println("Spring environments is initialized successfully.");
    }
}

/**
 * @Component注解的作用是创建一个对象，放入IOC容器中。
 $ @Bean注解并没有创建对象的能力，它只是获取某个方法的返回值，放入IOC容器中。
 */