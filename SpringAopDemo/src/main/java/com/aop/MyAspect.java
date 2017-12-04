package com.aop;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class MyAspect {
    public void WriteLog() {
        System.out.println("The log is write by Spring AOP");
    }

    public void before(JoinPoint jp) {
        System.out.println("->|Before advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
    }

    public void after(JoinPoint jp) {
        System.out.println("->|After advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
    }

    public void afterReturning(JoinPoint jp, Object money) {
        System.out.println("->|After returning advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
        System.out.println("Get RMB: " + money);
    }
}
