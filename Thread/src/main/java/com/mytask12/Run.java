package com.mytask12;

public class Run {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Main Thread's value = " + Tools.t.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA thread = new ThreadA();
            thread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
