package packagedemo.netprogrambasicdemo;

/**
 * Description:
 * basic net program
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/20
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        // 创建接收端的Socket对象
        DatagramSocket ds = new DatagramSocket(12345);

        while (true) {
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);

            ds.receive(dp);

            String ip = dp.getAddress().getHostAddress();
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println("from " + ip + " data is : " + s);
        }
    }
}

