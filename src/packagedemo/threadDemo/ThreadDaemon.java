/**
 * Description:
 * Thread Daemon
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
package packagedemo.threadDemo;

public class ThreadDaemon extends Thread {
    @Override
    public void run() {
        for (int x = 0; x < 10; x++) {
            System.out.println(getName() + " : " + x);
        }
    }
}
