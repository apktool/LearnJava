/**
 * Description:
 * net program
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/20
 */

package packagedemo.netprogramdemo;

import java.io.IOException;
import java.net.DatagramSocket;

public class ChatRoom {
    public static void main(String[] args) throws IOException {
        DatagramSocket dsSend = new DatagramSocket();
        DatagramSocket dsReceive = new DatagramSocket(12306);

        SendThread st = new SendThread(dsSend);
        ReceiveThread rt = new ReceiveThread(dsReceive);

        Thread t1 = new Thread(st);
        Thread t2 = new Thread(rt);

        t1.start();
        t2.start();
    }
}
