package com.apktool.jmx.mbean.notification.demo;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @author apktool
 * @package com.apktool.jmx.mbean.notification.demo
 * @class Jack
 * @description TODO
 * @date 2020-04-18 09:06
 */
public class Jack extends NotificationBroadcasterSupport implements JackMBean {
    private int seq = 0;

    public void hi() {
        //创建一个信息包
        Notification notify =
                //通知名称；谁发起的通知；序列号；发起通知时间；发送的消息
                new Notification("jack.hi", this, ++seq, System.currentTimeMillis(), "jack");
        sendNotification(notify);
    }
}
