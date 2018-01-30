package com.mytask5;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private static List list = new ArrayList<Object>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }
}
