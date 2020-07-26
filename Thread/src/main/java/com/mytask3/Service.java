package com.mytask3;

public class Service {
    public static void printA() throws InterruptedException {
        System.out.println("Thread-Name: start->| " + System.currentTimeMillis() + " >|< " + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("Thread-Name: end->| " + System.currentTimeMillis() + " >|< " + Thread.currentThread().getName());
    }

    public static void printB() throws InterruptedException {
        System.out.println("Thread-Name: start->| " + System.currentTimeMillis() + " >|< " + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("Thread-Name: end->| " + System.currentTimeMillis() + " >|< " + Thread.currentThread().getName());
    }
}

/**
 * synchronized(Class.class){...} 与 synchronized static 作用是一样的
 */
