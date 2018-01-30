package com.mytask4;

public class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object object) {
        super();
        this.lock = object;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("Start notify time= " + System.currentTimeMillis());
            lock.notify();
            System.out.println("End notify time= " + System.currentTimeMillis());
        }
    }
}
