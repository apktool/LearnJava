package com.mytask14;

public class Run {
    public static void main(String[] args) {
        try {
            MyService service = new MyService();
            ThreadA thread = new ThreadA(service);
            thread.start();

            Thread.sleep(3000);

            service.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
