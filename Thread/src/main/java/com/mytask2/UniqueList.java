package com.mytask2;

import java.util.ArrayList;
import java.util.List;

public class UniqueList {
    private List<String> list = new ArrayList();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized public int getSize() {
        return list.size();
    }
}
