package com.mytask11;

public class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Begin B Thread= "
                    + Thread.currentThread().getName() + " "
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("End B Thread= "
                    + Thread.currentThread().getName() + " "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
