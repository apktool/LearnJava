package packagedemo.threadlockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * thread lock
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class threadlock implements Runnable {
    private int tickets = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + (tickets--) + " is being show");
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
