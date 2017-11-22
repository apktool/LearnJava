package com.demo;

public class HelloApplication implements ApplicationInterface{
    private String name;

    @Override
    public void save() {
        System.out.println("HelloApplication | public void save()");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void init() {
        System.out.println("HelloApplication | public void init()");
    }

    public void destroy() {
        System.out.println("HelloApplication | public void destroy()");
    }

    public void printHello(){
        System.out.println("Hello world");
    }
}
