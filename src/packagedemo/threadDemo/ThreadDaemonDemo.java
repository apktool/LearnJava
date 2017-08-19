package packagedemo.threadDemo;

/**
 * Description:
 * Thread Daemon
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class ThreadDaemonDemo {
    public static void main(String[] args) {
        ThreadDaemon td1 = new ThreadDaemon();
        ThreadDaemon td2 = new ThreadDaemon();

        td1.setDaemon(true);
        td2.setDaemon(true);

        td1.start();
        td2.start();

        Thread.currentThread().setName("Main Daemon");

        for (int x = 0; x < 5; x++) {
            System.out.println(Thread.currentThread().getName() + x);
        }
    }
}
