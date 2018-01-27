package com.mytask1.Main;

import com.mytask1.Task;
import com.mytask1.MyThread1;
import com.mytask1.MyThread2;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        MyThread1 thread1 = new MyThread1(task);
        thread1.start();
        Thread.sleep(10);
        MyThread2 thread2 = new MyThread2(task);
        thread2.start();
    }
}
