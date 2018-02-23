package com.basicproxy;

public class Demo {
    public static void main(String[] args) {
        Image image = new ProxyImage("abc.jpg");
        image.display();
    }
}
