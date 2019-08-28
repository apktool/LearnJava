package com.chapter2;

public class MeaningOfThis {
    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(value);
                System.out.println(this.value);
                // this 指的是包含它的 Runnable ,而不是外面的类 MeaningOfThis
            }
        };
        r.run();
    }

    public static void main(String... args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
