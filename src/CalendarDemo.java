/**
 * Description:
 * Calendar
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.util.Calendar;
public class CalendarDemo {
    public static void main(String[] args){
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        int date = rightNow.get(Calendar.DATE);
        int time = rightNow.get(Calendar.HOUR);
        int minute = rightNow.get(Calendar.MINUTE);
        int seconde = rightNow.get(Calendar.SECOND);
        System.out.println(year + "-" + (month + 1) + "-" + date + " " + time + ":" + minute + ":" + seconde);
    }
}
