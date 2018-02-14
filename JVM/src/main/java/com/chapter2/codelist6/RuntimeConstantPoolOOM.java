/**
 * 方法区和运行时常量池溢出
 *
 * JVM args:
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * Note:
 * OpenJDK 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
 *
 * -XX:MetaspaceSize=2M
 * -XX:MaxMetaspaceSize=2M
 */

package com.chapter2.codelist6;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
