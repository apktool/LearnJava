package com.mytask27;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author apktool
 * @package com.mytask27
 * @class ReadWriteLockDemo
 * @description TODO
 * @date 2020-08-02 10:34
 */
public class ReadWriteLockDemo {
    public void handleRead(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                demo.handleRead(lock.readLock());
            }).start();
        }

        for (int i = 0 ; i< 20; i++) {
            new Thread(() -> {
                demo.handleWrite(lock.writeLock());
            }).start();
        }
    }
}
