package com.mytask22;

import java.util.concurrent.CountDownLatch;

/**
 * @author apktool
 * @date 2018-06-28 16:23
 * *
 * 利用CountDownLatch可以实现类似计数器的功能。
 * 比如一个任务需要等待其他指定的几个任务执行完毕之后才能执行。便可以使用CountDownLatch实现。
 *
 * 指定的任务可以使用 latch.countDown()来计数
 * 等待其他任务执行完成可以使用 await 来完成。
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }).start();

        try {
            latch.await();
            System.out.println("hello world");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello python");
    }
}
