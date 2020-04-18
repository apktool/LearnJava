package com.apktool.jmx.mbean.notification.demo;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * @author apktool
 * @package com.apktool.jmx.mbean.notification.demo
 * @class HelloListener
 * @description TODO
 * @date 2020-04-18 09:07
 */
public class HelloListener implements NotificationListener {
    public void handleNotification(Notification notification, Object handback) {
        if (handback instanceof Hello) {
            Hello hello = (Hello) handback;
            hello.sayHello(notification.getMessage());
        }
    }
}
