package com.aop;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class MyAspect {
    public void WriteLog() {
        System.out.println("The log is write by Spring AOP");
    }

    public void before(JoinPoint jp) {
        System.out.println("Before advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
    }
}
