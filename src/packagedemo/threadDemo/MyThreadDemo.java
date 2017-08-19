
/**
 * Description:
 * ThreadDemo
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/18
 */
package packagedemo.threadDemo;
public class MyThreadDemo {
    public static void main(String[] argv){
        MyTread my1 = new MyTread();
        // MyTread my1 = new MyTread("Thread_name");
        MyTread my2 = new MyTread();

        my1.setName("ahah");
        my2.setName("haha");

        my1.start();
        my2.start();

        System.out.println(Thread.currentThread().getName());
    }
}

/*
 * cd src
 * javac packagedemo\ThreadDemo\MyThreadDemo.java
 * java packagedemo.ThreadDemo.MyThreadDemo
 */