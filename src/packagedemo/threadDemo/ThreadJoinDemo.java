package packagedemo.threadDemo;

/**
 * Description:
 * Thread Join
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class ThreadJoinDemo {
    public static void main(String[] argv){
        ThreadJoin tj1 = new ThreadJoin();
        ThreadJoin tj2 = new ThreadJoin();
        ThreadJoin tj3 = new ThreadJoin();

        tj1.setName("haha");
        tj2.setName("ahah");
        tj3.setName("hehe");

        tj1.start();
        try{
            tj1.join(); // keeping continuously process-executed
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        tj2.start();
        tj3.start();
    }
}
