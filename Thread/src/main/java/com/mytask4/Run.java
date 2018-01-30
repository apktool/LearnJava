package com.mytask4;

public class Run {
    public static void main(String[] args) {
        try {
            Object lock = new Object();

            ThreadA t1 = new ThreadA(lock);
            t1.start();

            ThreadA.sleep(3000);

            ThreadB t2 = new ThreadB(lock);
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
