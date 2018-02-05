package com.mytask7;

public class Consumer {
    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println("Consumer: " + Thread.currentThread().getName() + " Waiting #");
                    lock.wait();
                }
                System.out.println("Consumer " + Thread.currentThread().getName() + " Runnable");
                ValueObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
