package com.mytask20;

import java.util.concurrent.Semaphore;

/**
 * 开启三个线程，交替打印 1,2,3,4,..,100
 */

public class SemaphoreABC {
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);
    private static int count = 0;

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    try {
                        A.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                    B.release();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    try {
                        B.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                    C.release();
                }
            }
        });

        Thread three = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    try {
                        C.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                    A.release();
                }
            }
        });

        one.start();
        two.start();
        three.start();
    }
}
