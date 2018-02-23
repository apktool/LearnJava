package com.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyImage implements InvocationHandler {
    private String fileName;
    private Image image;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public Object getInstance() {
        if (this.image == null) {
            this.image = new RealImage(fileName);
        }
        Class clazz = this.image.getClass();
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(image, args);
        return null;
    }
}
