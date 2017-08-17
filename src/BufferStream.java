/**
 * Description:
 * BufferStream, BufferedWrite, BufferedReader
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/16
 */

import java.io.*;

public class BufferStream {
    public static void main(String[] argv) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("bos.txt")
        );
        bos.write("Hello world".getBytes());
        bos.close();

        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(("bos.txt"))
        );

        int by = 0;
        while ((by = bis.read()) != -1) {
            System.out.print((char) by);
        }
        System.out.println("\n---------------");

        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            System.out.print(new String(bys, 0, len));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("bos.txt"));
        bw.write("hello");
        bw.write("java");
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader("bos.txt"));
        int ch = 0;
        while ((ch = br.read()) != -1) {
            System.out.print((char) ch);
        }
        br.close();

        BufferedReader bt = new BufferedReader(new FileReader("bos.txt"));
        char[] chs = new char[1024];
        int len1 = 0;
        while ((len1 = bt.read(chs)) != -1) {
            System.out.print(new String(chs, 0, len1));
        }
        bt.close();
    }
}
