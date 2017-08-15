/**
 * Description:
 * Exception
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDemo {
    public static void main(String[] args){
        String s = "2017-08-15";
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        try{
            Date d = sdf.parse(s);
            System.out.println(d);;
        }catch (ParseException e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println("----------");
            e.printStackTrace();
        }
    }
}