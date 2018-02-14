/**
 * Java堆内存溢出异常测试
 *
 * JVM args:
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=target
 *
 * -Xmx: JVM堆的最大内存
 * -Xms: JVM堆的最小内存
 * -XX:+HeapDumpOnOutOfMemoryError 当JVM出现内存溢出异常时，dump出当前的内存堆快照以便事后进行分析
 * --XX: HeapDumpPath=target 将快照文件保存到target目录下面
 */

package com.chapter2.codelist3;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject>  list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
