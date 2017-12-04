package com.aop;

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
}
