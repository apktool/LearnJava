package com.mytask6;

public class Run {
    public static void main(String[] args) {
        String lock = new String("");

        Producer producer = new Producer(lock);
        Consumer consumer = new Consumer(lock);

        ThreadProducer pThread = new ThreadProducer(producer);
        pThread.start();

        ThreadConsumer cThread = new ThreadConsumer(consumer);
        cThread.start();
    }
}
