package com.mytask3;

public class ThreadB extends Thread{
    @Override
    public void run() {
        try {
            Service.printB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
