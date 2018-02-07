package com.mytask11;

public class ThreadA extends Thread {
    private ThreadB b;

    public ThreadA(ThreadB b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            System.out.println("Begin A Thread= "
                    + Thread.currentThread().getName() + " "
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("End A Thread= "
                    + Thread.currentThread().getName() + " "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
