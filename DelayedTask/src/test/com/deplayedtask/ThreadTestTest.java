package com.deplayedtask;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author apktool
 * @date 2018-06-27 15:18
 */
class ThreadTestTest {
    private static final int threadNum = 10;
    private static CountDownLatch cdl = new CountDownLatch(threadNum);

    static class DelayMessage implements Runnable {

        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AppTest appTest = new AppTest();
            appTest.consumerDelayMessage();
        }
    }

    @Test
    void consumerDelayMessage() {
        AppTest appTest = new AppTest();
        appTest.productionDelayMessage();

        for (int i = 0; i< threadNum; i++) {
            new Thread(new DelayMessage()).start();
            System.out.println(i);
            cdl.countDown();
        }
    }
}