package com.function;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AuthorAnnotation {
    String name();
    String website() default "apktool.com";
    int revision() default 1;
}
