import java.io.*;

/**
 * Description:
 * BufferStream
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/16
 */
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
    }
}
