/**
 * 使用CGlib创建大量动态对象使方法区出现内存溢出异常
 *
 * JVM args:
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 */

package com.chapter2.codelist7;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class JavaMethodAreaOOM {
    static class OOMObject {
    }

    public static void main(final String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(objects, args);
                }
            });

            enhancer.create();
        }
    }
}
