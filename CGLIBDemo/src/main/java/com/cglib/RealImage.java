package com.cglib;

public class RealImage implements Image {
    private String fileName;

    public RealImage() {
        System.out.println(".+>" + fileName);
    }

    public RealImage(String fileName) {
        System.out.println("-->" + fileName);
        this.fileName = fileName;
    }

    public void display() {
        System.out.println(this.fileName + " is being display");
    }
}
