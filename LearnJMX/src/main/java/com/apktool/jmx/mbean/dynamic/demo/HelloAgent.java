package com.apktool.jmx.mbean.dynamic.demo;

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
        ObjectName helloName = new ObjectName("DynamicBean:name=hello");

        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);

        Thread.sleep(Integer.MAX_VALUE);
        System.out.println("....................jmx server start....................");
    }
}
