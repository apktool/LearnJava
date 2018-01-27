package com.mytask3;

public class ThreadA extends Thread{
    @Override
    public void run() {
        try {
            Service.printA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
