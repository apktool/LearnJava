package com.com.aop;

public class App {
    public static void main(String[] args) {
        CustomerServiceInterface service = new CustomerServiceImpl();
        CustomerServiceInterface proxy = (CustomerServiceInterface) JDKProxyUtils.getProxy(service);
        proxy.save();
        proxy.update();

        System.out.println("--------------------------");

        CustomerServiceImpl service1 = new CustomerServiceImpl();
        CustomerServiceImpl proxy1 = (CustomerServiceImpl) CglibProxyUtils.getProxy(service1);
        proxy1.save();
        proxy1.update();
    }
}
