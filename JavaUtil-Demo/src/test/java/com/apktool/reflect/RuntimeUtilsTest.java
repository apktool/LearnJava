package com.apktool.reflect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class RuntimeUtilsUser {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class RuntimeUtilsTest {

    @Test
    public void test1() {
        RuntimeUtilsUser user = new RuntimeUtilsUser();
        RuntimeUtils.writeFieldValue("id", 1, user);
        RuntimeUtils.writeFieldValue("name", "user1", user);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("user1", user.getName());
    }

    @Test
    public void test2() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("id", 1);
        map.put("name", "user");

        RuntimeUtilsUser user = new RuntimeUtilsUser();
        RuntimeUtils.parse(map, user);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("user", user.getName());
    }

    @Test
    public void test3() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("id", 1);
        map.put("name", "user");

        // 不支持嵌套类，比如把 RuntimeUtilsUser 写到某个类内部作为子类，会报错
        RuntimeUtilsUser user = RuntimeUtils.parse(map, RuntimeUtilsUser.class);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("user", user.getName());
    }
}