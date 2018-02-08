package com.mytask15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    volatile static private Integer count = 0;
    private boolean hasValue = false;

    public void producer() {
        try {
            lock.lock();
            while (hasValue == true) {
                condition.await();
            }
            System.out.println(count++ + " ###");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        try {
            lock.lock();
            while (hasValue == false) {
                condition.await();
            }
            System.out.println(count++ + " @@@");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
