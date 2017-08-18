/**
 * Description:
 * SequenceInputStream | merge files into new file
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/18
 */

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class SequenceInputStreamDemo {
    public static void main(String[] argv) throws IOException {
        InputStream s1 = new FileInputStream("a.txt");
        InputStream s2 = new FileInputStream("b.txt");

        SequenceInputStream sis = new SequenceInputStream(s1, s2);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("copy.txt")
        );

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = sis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        sis.close();

        System.out.println("---------------------");

        Vector<InputStream> v = new Vector<InputStream>();
        InputStream s3 = new FileInputStream("a.txt");
        InputStream s4 = new FileInputStream("b.txt");
        InputStream s5 = new FileInputStream("c.txt");
        v.add(s3);
        v.add(s4);
        v.add(s5);
        Enumeration<InputStream> en = v.elements();
        SequenceInputStream sis1 = new SequenceInputStream(en);
        BufferedOutputStream bos1 = new BufferedOutputStream(
                new FileOutputStream("copy.txt")
        );

        byte[] bys1 = new byte[1024];
        int len1 = 0;
        while ((len1 = sis1.read(bys1)) != -1) {
            bos1.write(bys1, 0, len1);
        }
        bos1.close();
        sis1.close();
    }
}
