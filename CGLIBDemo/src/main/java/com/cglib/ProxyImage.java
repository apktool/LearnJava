package com.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyImage implements MethodInterceptor {
    private String fileName;
    private Image image;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public Object getInstance() {
        if (image == null) {
            this.image = new RealImage();
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.image.getClass());
        enhancer.setCallback(this);
        // 等价于 Object object = (Object)new RealImage()
        // Object object = enhancer.create();

        // 等价于 Object object = (Object)new RealImage(this.fileName)
        Class[] type = {String.class};
        String[] parameter = {this.fileName};
        Object object = enhancer.create(type, parameter);

        return object;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(o, objects);
        return null;
    }
}
