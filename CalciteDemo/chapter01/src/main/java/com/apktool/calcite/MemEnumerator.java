package com.apktool.calcite;

import java.util.List;
import java.util.Map;

import org.apache.calcite.linq4j.Enumerator;

public class MemEnumerator<E> implements Enumerator<E> {

    private final List<Map<String, Object>> list;
    private int index = -1;
    private E e;

    public MemEnumerator(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public E current() {
        return e;
    }

    @Override
    public boolean moveNext() {
        if (index + 1 >= list.size()) {
            return false;
        } else {
            e = (E) list.get(index + 1).values().toArray();
            index++;
            return true;
        }
    }

    @Override
    public void reset() {
        index = -1;
        e = null;
    }

    @Override
    public void close() {

    }
}