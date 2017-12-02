package com.com.aop;

public class App {
    public static void main(String[] args) {
        CustomerServiceInterface service = new CustomerServiceImpl();
        CustomerServiceInterface proxy = (CustomerServiceInterface) JDKProxyUtils.getProxy(service);
        proxy.save();
        proxy.update();
    }
}
