package com.mytask20;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 开启三个线程，交替打印 1,2,3,4,..,100
 */

public class LockABC {
    private static Lock lock = new ReentrantLock();// 通过JDK5中的Lock锁来保证线程的访问的互斥
    private static int count = 0;

//    static class ThreadA extends Thread {
//        @Override

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    lock.lock();
                    while (count % 3 == 0) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                    }
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    lock.lock();
                    while (count % 3 == 1) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                    }
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        });

        Thread three = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    lock.lock();
                    while (count % 3 == 2) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                    }
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        });

        one.start();
        two.start();
        three.start();

    }
}
