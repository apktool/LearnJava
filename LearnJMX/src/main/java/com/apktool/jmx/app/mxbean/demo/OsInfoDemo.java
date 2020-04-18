package com.apktool.jmx.app.mxbean.demo;

import com.sun.management.UnixOperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @author apktool
 * @package com.apktool.jmx.app.mxbean.demo
 * @class OsInfoDemo
 * @description TODO
 * @date 2020-04-18 15:00
 */
public class OsInfoDemo {
    public static void main(String[] args) {
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();

        if (os instanceof UnixOperatingSystemMXBean) {
            UnixOperatingSystemMXBean unixOs = (UnixOperatingSystemMXBean) os; //10240
            System.out.println(unixOs.getMaxFileDescriptorCount()); //37
            System.out.println(unixOs.getOpenFileDescriptorCount()); //8
            System.out.println(unixOs.getArch()); //x86_64
            System.out.println(unixOs.getAvailableProcessors()); //8
            System.out.println(unixOs.getName()); //Mac OS X
            System.out.println(unixOs.getSystemCpuLoad()); //2.658203125
            System.out.println(unixOs.getVersion()); //10.13.3
            System.out.println(unixOs.getCommittedVirtualMemorySize()); //10345111552
            System.out.println(unixOs.getFreePhysicalMemorySize()); //2892271616
            System.out.println(unixOs.getProcessCpuLoad()); //0.0
            System.out.println(unixOs.getTotalSwapSpaceSize()); //8589934592
            System.out.println(unixOs.getTotalPhysicalMemorySize()); //17179869184
            System.out.println(unixOs.getSystemCpuLoad()); //0.0
        }

        System.out.println("--------------------------------------------");

        if (os instanceof OperatingSystemMXBean) {
            System.out.println(os.getArch());
            System.out.println(os.getAvailableProcessors());
            System.out.println(os.getName());
            System.out.println(os.getVersion());
        }
    }
}
