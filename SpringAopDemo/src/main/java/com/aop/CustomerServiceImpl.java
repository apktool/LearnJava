package com.aop;

public class CustomerServiceImpl implements CustomerServiceInterface{
    public void save() {
        System.out.println("CustomerServiceImpl -> save()");
    }

    public void update() {
        System.out.println("CustomerServiceImpl -> update");
    }
}
