package com.mytask25;

import java.util.ArrayList;
import java.util.List;

/**
 * N个线程执行一个job
 */

public class MultiThreadSingleTask {
    public static void main(String[] args) throws InterruptedException {
        int concurrentNum = 10;
        int tasks = 2;
        int group = concurrentNum / tasks;

        List<Thread> list = new ArrayList<Thread>(concurrentNum);

        int pre = 0;
        int now = pre;

        while (now < concurrentNum) {
            now = pre + group;
            for (int i = pre; i < now; i++) {
                list.add(new Thread(new WorkerThread(pre)));
            }
            pre = now;
        }

        for (int i = 0; i < concurrentNum; i++) {
            list.get(i).start();
        }

        for (int i = 0; i < concurrentNum; i++) {
            list.get(i).join();
        }
    }
}

class WorkerThread implements Runnable {
    private int cnt;

    public WorkerThread(int cnt) {
        this.cnt = cnt;
    }

    public void run() {
        System.out.printf("---> | %d | <-----\n", cnt);
    }
}
