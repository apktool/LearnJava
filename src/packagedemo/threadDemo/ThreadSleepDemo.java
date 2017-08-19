package packagedemo.threadDemo;

/**
 * Description:
 * Thread Sleep
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class ThreadSleepDemo {
    public static void main(String[] argv){
        ThreadSleep ts1 = new ThreadSleep();
        ThreadSleep ts2 = new ThreadSleep();
        ThreadSleep ts3 = new ThreadSleep();

        ts1.setName("ts1");
        ts2.setName("ts2");
        ts3.setName("ts3");

        ts1.start();
        ts2.start();
        ts3.start();
    }
}
