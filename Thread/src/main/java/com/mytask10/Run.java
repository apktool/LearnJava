package com.mytask10;

public class Run {
    public static void main(String[] args) {
        try {
            MyThread threadTest = new MyThread();
            threadTest.start();
            threadTest.join();
            System.out.println("This line will be print finally.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
