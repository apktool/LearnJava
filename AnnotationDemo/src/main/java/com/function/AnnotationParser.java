package com.function;

import java.lang.reflect.Method;

public class AnnotationParser {
    public static void main(String[] args) throws SecurityException, ClassNotFoundException {
        String clazz = "com.function.AnnotationDemo";
        Method[]  demoMethod = AnnotationParser.class.getClassLoader().loadClass(clazz).getMethods();

        for (Method method : demoMethod) {
            if (method.isAnnotationPresent(AuthorAnnotation.class)) {
                AuthorAnnotation authorInfo = method.getAnnotation(AuthorAnnotation.class);
                System.out.println("method: "+ method);
                System.out.println("name="+ authorInfo.name()
                        + ", website="+ authorInfo.website()
                        + ", revision="+authorInfo.revision());
            }
        }
    }
}
