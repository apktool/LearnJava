package packagedemo.threadDemo;

import java.util.Date;

/**
 * Description:
 * Thread interrupt
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class ThreadStop extends Thread {
    @Override
    public void run() {
        System.out.println("Start" + new Date());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("The thread is killed");
        }

        System.out.println("End" + new Date());
    }
}
