package com.mytask12;

public class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 10; i < 20; i++) {
                System.out.println("ThreadB's value = " + Tools.t.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
