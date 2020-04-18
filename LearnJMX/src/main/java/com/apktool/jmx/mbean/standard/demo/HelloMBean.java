package com.apktool.jmx.mbean.standard.demo;

/**
 * @author apktool
 * @package com.apktool.jmx.mbean.standard.demo
 * @class HelloMXBean
 * @description TODO
 * @date 2020-04-17 01:00
 */
public interface HelloMBean {
    public String getName();

    public void setName(String name);

    public void sayHello();

    public void sayHello(String name);

    public String getTelephone();
}
