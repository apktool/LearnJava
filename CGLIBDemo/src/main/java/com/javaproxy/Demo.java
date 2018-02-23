package com.javaproxy;

public class Demo {
    public static void main(String[] args) {
        Image image = (Image) new ProxyImage("abc.jpg").getInstance();
        image.display();
    }
}
