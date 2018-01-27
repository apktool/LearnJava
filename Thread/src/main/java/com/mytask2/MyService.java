package com.mytask2;

public class MyService {
    public UniqueList addServiceMethod(UniqueList list, String data) {
        try {
            synchronized (list) {
                if (list.getSize() < 1) {
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }
}
