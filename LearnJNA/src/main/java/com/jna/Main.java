package com.jna;

/**
 * @author li.wengang
 * @date 2018-11-26 17:05
 */

import com.sun.jna.Library;
import com.sun.jna.Native;

public class Main {

    public static void main(String[] args) {
        int res;
        res = CLibrary.INSTANCE.sum(10, 2);
        System.out.println(res);

        res = CLibrary.INSTANCE.sub(10, 2);
        System.out.println(res);
    }

    private interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load("/tmp/libdemo.so", CLibrary.class);

        int sum(int a, int b);

        int sub(int a, int b);
    }
}
