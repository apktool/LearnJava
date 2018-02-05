package com.mytask7;


public class Producer {
    private String lock;

    public Producer(String lock) {
        super();
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObject.value.equals("")) {
                    System.out.println("Producer: " + Thread.currentThread().getName() + " Waiting *");
                    lock.wait();
                }

                System.out.println("Producer: " + Thread.currentThread().getName() + " Runnable");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                ValueObject.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
