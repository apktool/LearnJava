package com.apktool.jmx.app.mxbean.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.PlatformManagedObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author apktool
 * @package com.apktool.jmx.app.mxbean.demo
 * @class JvmInfoDemo
 * @description this content come from elasticsearch, Jvminfo.java
 * @date 2020-04-18 15:58
 */
public class JvmInfoDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("unchecked") Class<? extends PlatformManagedObject> clazz =
                (Class<? extends PlatformManagedObject>) Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
        Class<?> vmOptionClazz = Class.forName("com.sun.management.VMOption");
        PlatformManagedObject hotSpotDiagnosticMXBean = ManagementFactory.getPlatformMXBean(clazz);
        Method vmOptionMethod = clazz.getMethod("getVMOption", String.class);
        Method valueMethod = vmOptionClazz.getMethod("getValue");

        Object onErrorObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "OnError");
        String onError = (String) valueMethod.invoke(onErrorObject);
        System.out.println(onError);

        Object onOutOfMemoryErrorObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "OnOutOfMemoryError");
        String onOutOfMemoryError = (String) valueMethod.invoke(onOutOfMemoryErrorObject);
        System.out.println(onOutOfMemoryError);

        Object useCompressedOopsVmOptionObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "UseCompressedOops");
        String useCompressedOops = (String) valueMethod.invoke(useCompressedOopsVmOptionObject);
        System.out.println(useCompressedOops);

        Object useG1GCVmOptionObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "UseG1GC");
        String useG1GC = (String) valueMethod.invoke(useG1GCVmOptionObject);
        System.out.println(useG1GC);

        Object initialHeapSizeVmOptionObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "InitialHeapSize");
        String configuredInitialHeapSize = (String) valueMethod.invoke(initialHeapSizeVmOptionObject);
        System.out.println(configuredInitialHeapSize);

        Object maxHeapSizeVmOptionObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "MaxHeapSize");
        String configuredMaxHeapSize = (String) valueMethod.invoke(maxHeapSizeVmOptionObject);
        System.out.println(configuredMaxHeapSize);

        Object useSerialGCVmOptionObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "UseSerialGC");
        String useSerialGC = (String) valueMethod.invoke(useSerialGCVmOptionObject);
        System.out.println(useSerialGC);
    }
}
