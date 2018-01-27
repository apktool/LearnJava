package com.mytask2;

public class MyThread2 extends Thread{
    private UniqueList list;

    public MyThread2(UniqueList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(list, "B");
    }
}
