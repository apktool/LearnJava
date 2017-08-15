/**
 * Description:
 * Exception, throws, throw
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDemo {
    public static void main(String[] args) throws Exception {
        String s = "2017-08-15";
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(s);
            System.out.println(d);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println("----------");
            e.printStackTrace();
        }

        System.out.println("----------");

        try {
            method();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------");
    }

    public static void method() throws Exception {
        int a = 10;
        int b = 0;
        if (b == 0) {
            throw new Exception();
        }
        System.out.println(a / b);
    }
}