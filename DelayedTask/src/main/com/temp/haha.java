package com.temp;

import java.util.Calendar;

/**
 * @author apktool
 * @date 2018-06-28 15:50
 */
public class haha {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -33);
        System.out.println(cal.getTimeInMillis());
    }
}
