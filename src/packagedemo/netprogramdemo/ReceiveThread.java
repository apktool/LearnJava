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
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveThread implements Runnable {
    private DatagramSocket ds;

    public ReceiveThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] bys = new byte[1024];
                DatagramPacket dp = new DatagramPacket(bys, bys.length);

                ds.receive(dp);

                String ip = dp.getAddress().getHostAddress();
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println("from " + ip + " data is : " + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
