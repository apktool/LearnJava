package com.mytask7;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");

        Producer producer = new Producer(lock);
        Consumer consumer = new Consumer(lock);

        ThreadProducer[] pThread = new ThreadProducer[2];
        ThreadConsumer[] cThread = new ThreadConsumer[2];

        for (int i = 0; i < 2; i++) {
            pThread[i] = new ThreadProducer(producer);
            pThread[i].setName("Producer-> " + (i + 1));
            pThread[i].start();

            cThread[i] = new ThreadConsumer(consumer);
            cThread[i].setName("Consumer-> " + (i + 1));
            cThread[i].start();
        }

        Thread.sleep(5000);

        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);

        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName() + " " + threadArray[i].getState());
        }
    }
}
