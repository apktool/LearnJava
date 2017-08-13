/**
 * Description:
 * Date
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) throws ParseException{
        Date d = new Date();
        System.out.println(d);

        d.setTime(1000);
        System.out.println(d);

        System.out.println(d.getTime());
        System.out.println(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(d);
        System.out.println(s);

        String str = "2017-08-13 11:20:00";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ss = sdf2.parse(str);
        System.out.println(ss);
    }
}
