package com.mytask1;

import com.mytask1.Task;

public class MyThread1 extends Thread{
    private Task task;

    public MyThread1(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}