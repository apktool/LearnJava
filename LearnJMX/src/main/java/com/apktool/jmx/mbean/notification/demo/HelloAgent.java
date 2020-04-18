package com.apktool.jmx.mbean.notification.demo;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @author apktool
 * @package com.apktool.jmx.mbean.standard.demo
 * @class HelloAgent
 * @description TODO
 * @date 2020-04-17 01:01
 */
public class HelloAgent {
    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException, InterruptedException {
        //create mbean server
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        //create object name
        ObjectName helloName = new ObjectName("MBean:name=hello");

        Hello hello = new Hello();
        server.registerMBean(hello, helloName);

        Jack jack = new Jack();
        server.registerMBean(jack, new ObjectName("Notification:name=Jack"));

        jack.addNotificationListener(new HelloListener(), null, hello);

        System.out.println("....................jmx server start....................");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
