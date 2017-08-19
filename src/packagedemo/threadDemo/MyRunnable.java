package packagedemo.threadDemo;

/**
 * Description:
 * Thread Runnable
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class MyRunnable implements Runnable{
    @Override
    public void run(){
        for(int x=0; x<10; x++){
            System.out.println(Thread.currentThread().getName() + ":" + x);
        }
    }
}
