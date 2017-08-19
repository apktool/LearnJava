package packagedemo.threadDemo;

/**
 * Description:
 * Thread interrupt
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class ThreadStopDemo {
    public static void main(String[] argv) {
        ThreadStop ts = new ThreadStop();
        ts.start();
        try {
            Thread.sleep(3000);
            // ts.stop();
            ts.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
