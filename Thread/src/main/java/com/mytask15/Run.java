package com.mytask15;

public class Run {
    public static void main(String[] args) {
        MyService service = new MyService();

        MyThreadA myThreadA = new MyThreadA(service);
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(service);
        myThreadB.start();
    }
}
