package com.chapter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupByDemo {
    public static void main(String[] args) {
        System.out.println("--------Word Count------------");

        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya", "papaya", "papaya");
        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);

        System.out.println("--------Word order By Counts------------");

        Map<String, Long> finalMap = new LinkedHashMap();
        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println(finalMap);
    }
}
