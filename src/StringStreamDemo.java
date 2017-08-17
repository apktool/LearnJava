/**
 * Description:
 * StringStream
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/17
 */

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringStreamDemo {
    public static void main(String[] argv) throws UnsupportedEncodingException {
        String s = "无名";
        byte[] bys = s.getBytes("UTF-8");
        System.out.println(Arrays.toString(bys));

        String ss = new String(bys, "UTF-8");
        System.out.println(ss);
    }
}
