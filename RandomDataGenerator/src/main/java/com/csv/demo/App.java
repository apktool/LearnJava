package com.csv.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author apktool
 * @package com.csv.demo
 * @class A
 * @description TODO
 * @date 2020-07-28 20:40
 */
public class App {

    public static void main(String[] args) {
        Integer fileNum = 5;
        Integer rowNum = 10;

        ThreadFactory namedThreadFactory = new ThreadFactory() {
            AtomicInteger fileCounter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new FileCreatorThread(r, "/tmp/abc/", fileCounter.get() * rowNum, rowNum);
                thread.setName("csv-generator-thread-" + fileCounter.incrementAndGet());
                return thread;
            }
        };

        ExecutorService threadPool = new ThreadPoolExecutor(fileNum, 10, 60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future> list = new ArrayList<>(fileNum);

        for (int i = 0; i < fileNum; i++) {
            Future submit = threadPool.submit(new FileCreatorThread());
            list.add(submit);
        }

        long count = list.stream().filter(t -> t.isDone()).count();
        System.out.println(count);
    }
}
