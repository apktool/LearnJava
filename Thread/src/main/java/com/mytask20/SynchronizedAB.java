package com.mytask20;

/**
 * 开启两个线程，一个线程打印1到100的奇数。如1，3，7…99. 另外一个线程打印1到100的偶数。如2，4，6…100.
 * 1到100的数字最终打印出来格式是1,2,3,4,5…100. 要求用synchronized和wait实现.
 */

public class SynchronizedAB {
    private static int[] share = new int[1];
    private static int count = 0;

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    synchronized (share) {
                        share.notifyAll();
                        System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                        try {
                            share.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            public void run() {
                while (count < 100) {
                    synchronized (share) {
                        share.notifyAll();
                        System.out.println("-> " + Thread.currentThread().getName() + ": " + count++);
                        try {
                            share.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        one.start();
        two.start();
    }
}
