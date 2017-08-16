/**
 * Description:
 * file exception | write, read
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/16
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamDemo {
    public static void main(String[] argv) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("fos.txt");
            fos.write("Hello World".getBytes());
            fos.write("\n".getBytes());

            fos.write(97);
            fos.write("\n".getBytes());

            byte[] bys = {97, 98, 99, 100, 110};
            fos.write(bys);
            fos.write(bys, 1, 3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis = new FileInputStream("fos.txt");
        byte[] bys = new byte[5];
        int len = fis.read(bys);
        System.out.println(new String(bys, 0, len));
    }
}
