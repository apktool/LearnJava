/**
 * Description:
 * Thread Sleep
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */

package packagedemo.threadDemo;
import java.util.Date;
public class ThreadSleep extends Thread{
    public void run(){
        for (int x=0; x<100; x++){
            System.out.println(getName() + " " + new Date());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
