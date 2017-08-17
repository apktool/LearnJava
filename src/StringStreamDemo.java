/**
 * Description:
 * StringStream, OutputStreamWriter, InputStreamReader
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/17
 */

import java.io.*;
import java.util.Arrays;

public class StringStreamDemo {
    public static void main(String[] argv) throws IOException {
        String s = "无名";
        byte[] bys = s.getBytes("UTF-8");
        System.out.println(Arrays.toString(bys));

        String ss = new String(bys, "UTF-8");
        System.out.println(ss);

        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("osw.txt"), "UTF-8"
        );
        osw.write('a');
        osw.write(97);
        osw.flush();
        osw.close();

        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("osw.txt"), "UTF-8"
        );

        int ch = 0;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }
        System.out.println();
    }
}
