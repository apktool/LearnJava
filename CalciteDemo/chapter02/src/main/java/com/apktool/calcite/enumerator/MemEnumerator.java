package com.apktool.calcite.enumerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.calcite.linq4j.Enumerator;

public class MemEnumerator<E> implements Enumerator<E> {
    private List<Map<String, Object>> list = new ArrayList<>(16);
    private int index = -1;
    private E e;

    private static final Object[][] rows = {
            {1, "red", 10},
            {2, "yellow", 5},
            {3, "red", 12},
            {4, "blue", 3},
            {5, "red", 3}
    };

    public MemEnumerator() {
        for (int i = 0; i < rows.length; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("ID", rows[i][0]);
            map.put("color", rows[i][1]);
            map.put("units", rows[i][2]);
            list.add(map);
        }
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