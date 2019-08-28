package com.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupDemo {
    public static void main(String[] args) {
        String[] str = {"Hello", "world", "Hello", "Python", "Hello", "Java", "Hello", "Python"};

        System.out.println("-------------World Count---------------");

        Map<String, Long> a = Arrays.asList(str).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        a.entrySet().stream().forEach(t -> System.out.println(t.getKey() + " : " + t.getValue()));

        System.out.println("------------World Count == 5--------------");

        Map<Boolean, List<String>> b = Arrays.asList(str).stream().collect(Collectors.partitioningBy(s -> s.length() == 5));
        b.entrySet().stream().forEach(t -> System.out.println(t.getKey() + " : " + t.getValue()));


        System.out.println("-------------World Length---------------");

        str = new String[]{"a", "bc", "efg", "d", "hi", "jkl"};

        Map<String, Integer> c = Arrays.asList(str).stream().collect(Collectors.toMap(Function.identity(), String::length));
        c.entrySet().stream().forEach(t -> System.out.println(t.getKey() + " : " + t.getValue()));
    }
}
