package com.com.aop;

public class CustomerServiceImpl implements CustomerServiceInterface{
    @Override
    public void save() {
        System.out.println("CustomerServiceImple -> save()");
    }

    @Override
    public void update() {
        System.out.println("CustomerServiceImple -> update()");
    }
}
