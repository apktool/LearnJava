package com.mytask12;

public class ThreadLocalExt extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return "I am default value";
    }
}
