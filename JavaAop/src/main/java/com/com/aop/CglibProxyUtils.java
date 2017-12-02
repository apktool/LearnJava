package com.com.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyUtils {
    public static Object getProxy(final Object target){
        return Enhancer.create(
                CustomerServiceImpl.class,
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("Logging");
                        return method.invoke(target, objects);
                    }
                }
        );
    }
}
