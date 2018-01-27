package com.mytask2;

public class MyThread1 extends Thread {
    private UniqueList list;

    public MyThread1(UniqueList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(list, "A");
    }
}
