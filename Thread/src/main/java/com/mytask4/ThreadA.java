package com.mytask4;

public class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object object) {
        super();
        this.lock = object;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("Start wait= " + System.currentTimeMillis());
                lock.wait();
                System.out.println("End wait= " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
