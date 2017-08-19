package packagedemo.threadDemo;

/**
 * Description:
 * ThreadDemo
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/18
 */
public class MyTread extends Thread{
    @Override
    public void run() {
        for(int x = 0; x<10; x++){
            System.out.println(getName() + "<-->" + x);
        }
    }
}