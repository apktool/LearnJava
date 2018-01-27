package com.mytask3;

public class Run {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.setName("B");
        threadB.start();
    }
}
