package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MyAspect {
    public void WriteLog() {
        System.out.println("The log is write by Spring AOP");
    }

    @Before(value = "execution(public void com.aop.CustomerServiceImpl.*())")
    public void before(JoinPoint jp) {
        System.out.println("->|Before advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
    }

    @After(value = "execution(public void com.aop.CustomerServiceImpl.*())")
    public void after(JoinPoint jp) {
        System.out.println("->|After advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
    }

    @AfterReturning(value = "execution(public Double com.aop.CustomerServiceImpl.*(..)) && args(money))")
    public void afterReturning(JoinPoint jp, Object money) {
        System.out.println("->|After returning advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
        System.out.println("Get RMB: " + money);
    }

    @AfterThrowing(value = "execution(public * com.aop.CustomerServiceImpl.*(..)) && args(e))")
    public void afterThrowing(JoinPoint jp, Throwable e) {
        System.out.println("->|After throwing advice");
        System.out.println("Proxy object type: " + jp.getThis().getClass());
        System.out.println("The function intercepted: " + jp.getSignature().getName());
        System.out.println("The args intercepted" + Arrays.asList(jp.getArgs()));
        System.out.println("Something is wrong: " + e.getMessage());
    }

    @Around(value = "execution(public void com.aop.CustomerServiceImpl.*())")
    public void aroundAdvice(ProceedingJoinPoint pjp){
        System.out.println("------------>|");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("|<------------");
    }
}
