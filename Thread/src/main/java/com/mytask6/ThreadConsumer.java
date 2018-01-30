package com.mytask6;

public class ThreadConsumer extends Thread {
    private Consumer consumer;

    public ThreadConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            consumer.getValue();
        }
    }
}
