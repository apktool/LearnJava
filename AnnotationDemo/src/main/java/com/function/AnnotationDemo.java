package com.function;

public class AnnotationDemo {
    /*
    @AuthorAnnotation(name="apktool", website="apktool.io", revision=1)
    public static void main(String[] args) {
        System.out.println("I am main method");
    }
    */

    @SuppressWarnings({ "unchecked", "deprecation" })
    @AuthorAnnotation(name="google", website="google.com", revision=2)
    public void demo(){
        System.out.println("I am demo method");
    }
}
