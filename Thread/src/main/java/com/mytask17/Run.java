package com.mytask17;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Run {
//    private static Timer timer = new Timer(true); //守护进程
    private static Timer timer = new Timer();
    static public class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("The time is wasted: " + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTask task = new MyTask();

            String dateString = "2018-02-09 21:02:00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateRef = sdf.parse(dateString);

            System.out.println(dateRef.toLocaleString() + " || " + new Date());

            timer.schedule(task, dateRef);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
