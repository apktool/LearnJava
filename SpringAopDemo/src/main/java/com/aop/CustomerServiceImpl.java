package com.aop;

import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerServiceInterface{
    public void save() {
        System.out.println("CustomerServiceImpl -> save()");
    }

    public void update() {
        System.out.println("CustomerServiceImpl -> update");
    }

    public Double transfer(Double money) {
        System.out.println("Transfer RMB: " + money);
        return money;
    }

    public void error() {
        int a = 100/0;
        System.out.println("Error occur whiling executing.");
    }
}
