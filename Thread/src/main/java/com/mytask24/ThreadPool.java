package com.mytask24;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池中
 *
 * 最大运行线程数量 = maximumPoolSize
 * 最大阻塞线程数量 = queueSize
 * 线程池中最大线程总量 = maximumPoolSize + queueSize
 *
 * @see <a href="https://segmentfault.com/a/1190000008693801">Java ThreadPoolExecutor 线程池源码分析</a>
 */

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 3;
        int maximumPoolSize = 3;
        long keepAliveTime = 0L;
        int queueSize = 3;
        TimeUnit unit = TimeUnit.SECONDS;

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(queueSize);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new WorkThreadFactory());

        for (int i = 0; i < 10; i++) {
            executor.execute(new Handler());
        }


        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");
    }
}

class Handler implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class WorkThreadFactory implements ThreadFactory {
    private final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    WorkThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);

        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
