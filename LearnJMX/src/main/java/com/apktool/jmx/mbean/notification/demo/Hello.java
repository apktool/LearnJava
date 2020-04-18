package com.apktool.jmx.mbean.notification.demo;

/**
 * @author apktool
 * @package com.apktool.jmx.mbean.standard.demo
 * @class Hello
 * @description TODO
 * @date 2020-04-17 01:01
 */
public class Hello implements HelloMBean {
    private String name;
    private String telephone ="1351201201";

    public String getName() {
        return name;
    }

    public void sayHello() {
        System.out.println("print......"+name);

    }

    public void sayHello(String name) {
        System.out.println("print......."+name);

    }

    public void setName(String name) {
        this.name = name;
        System.out.println("My value is set to "+name);

    }

    public String getTelephone() {
        return telephone;
    }
}
