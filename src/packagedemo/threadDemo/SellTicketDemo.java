package packagedemo.threadDemo;

/**
 * Description:
 * Thread synchronized
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class SellTicketDemo {
    public static void main(String[] argv) {
        SellTicket st = new SellTicket();

        Thread t1 = new Thread(st, "Windows1 ");
        Thread t2 = new Thread(st, "Windows2 ");
        Thread t3 = new Thread(st, "Windows3 ");

        t1.start();
        t2.start();
        t3.start();
    }
}
