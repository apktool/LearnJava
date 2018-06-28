package com.mytask22;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author apktool
 * @date 2018-06-28 16:33
 **
 * 利用CyclicBarrier可以实现“回环栅栏”的功能。
 * 比如一个任务需要等待一组任务执行完毕之后才能执行。
 *
 * 等待多少个任务可以使用new CyclicBarrier(N)实现
 * 等待其他任务执行完成可以使用 await 来完成。
 */
public class CyclicBarrierTest {
    final static int N = 4;
    final static CyclicBarrier barrier = new CyclicBarrier(N);

    public static class Writer extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("All Threads have been finished!!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            new Writer().start();
        }

        System.out.println("Hello world");
    }
}
