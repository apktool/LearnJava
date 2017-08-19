package packagedemo.threadlockdemo;

/**
 * Description:
 * thread lock
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class threadlockdemo {
    public static void main(String[] argv){
        threadlock st = new threadlock();

        Thread t1 = new Thread(st, "window1: ");
        Thread t2 = new Thread(st, "window2: ");
        Thread t3 = new Thread(st, "window3: ");

        t1.start();
        t2.start();
        t3.start();
    }
}
