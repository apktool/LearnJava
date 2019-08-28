package com.chapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {
    public static void main(String[] args) {
        Integer[] array1 = {12, 32, 23, 43, 34, 54, 45};
        List<Integer> list1 = Arrays.asList(array1);

        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        list1.forEach(t -> System.out.print(t + " "));

        System.out.println("\n--------------------------");

        Integer[] array2 = {12, 32, 23, 43, 34, 54, 45};
        List<Integer> list2 = Arrays.asList(array2);

        list2.sort(Comparator.comparingInt(Integer::intValue).reversed());
        list2.forEach(t -> System.out.print(t + " "));
    }
}
