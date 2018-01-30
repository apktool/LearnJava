package com.mytask5;

public class ThreadA extends Thread{
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            if (MyList.size() != 5) {
                System.out.println("Wait begin= " + System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Wait end= " + System.currentTimeMillis());
            }
        }
    }
}
