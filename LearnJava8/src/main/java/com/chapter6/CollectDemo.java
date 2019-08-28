package com.chapter6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CollectDemo {
    public static void main(String[] args) {
        Integer[] nums = {3, 1, 2, 4, 7, 5, 6, 0};

        System.out.println("-------------Arrays Count---------------");

        Long a = Arrays.asList(nums).stream().collect(Collectors.counting());
        System.out.println(a);

        System.out.println("-------------Maximum of Arrays---------------");

        Integer b = Arrays.asList(nums).stream().collect(Collectors.maxBy(Comparator.comparingInt(s -> s))).get();
        System.out.println(b);

        System.out.println("-------------Minimum of Arrays---------------");

        Integer c = Arrays.asList(nums).stream().collect(Collectors.minBy(Comparator.comparingInt(s -> s))).get();
        System.out.println(c);

        System.out.println("-------------Average of Arrays---------------");

        Double d = Arrays.asList(nums).stream().collect(Collectors.averagingInt(s -> s));
        System.out.println(d);

        System.out.println("-------------Joining String---------------");

        String[] str = {"a", "b", "c", "d", "e"};
        String e = Arrays.asList(str).stream().collect(Collectors.joining(" "));
        System.out.println(e);

        String f = Arrays.asList(str).stream().reduce((s1, s2) -> s1 + " " + s2).get();
        System.out.println(f);
    }
}
