package packagedemo.threadDemo;

/**
 * Description:
 * Thread Runnable
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class MyRunnableDemo {
    public static void main(String[] argv) {
        MyRunnable my = new MyRunnable();
        Thread t1 = new Thread(my);
        Thread t2 = new Thread(my);

        t1.start();
        t2.start();
    }
}
