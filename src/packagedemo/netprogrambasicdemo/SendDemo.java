package packagedemo.netprogrambasicdemo;

/**
 * Description:
 * basic net program
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/20
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = br.readLine()) != null) {
            if ("886".equals(line)) {
                break;
            }

            byte[] bys = line.getBytes();
            // DatagramPacket dp = new DatagramPacket(bys, bys.length,
            // InetAddress.getByName("192.168.12.92"), 12345);
            DatagramPacket dp = new DatagramPacket(bys, bys.length,
                    InetAddress.getByName("192.168.99.77"), 12345);

            ds.send(dp);
        }

        ds.close();
    }
}
