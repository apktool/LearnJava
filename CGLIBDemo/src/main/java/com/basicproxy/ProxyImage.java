package com.basicproxy;

public class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (this.realImage == null) {
            this.realImage = new RealImage(fileName);
        }
        this.realImage.display();
    }
}
