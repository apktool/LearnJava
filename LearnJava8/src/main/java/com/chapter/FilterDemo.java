package com.chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class FilterDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("2");
        list.add("1");
        list.add("4");
        list.add("3");

        list.sort(String::compareToIgnoreCase);

        list.stream().forEach(item -> System.out.print(item));

        System.out.println();

        list.stream().forEach(System.out::print);

        System.out.println();

        List<String> list1 = list.parallelStream()
                .filter(item -> Integer.valueOf(item) > 2)
                .collect(toList());

        list1.forEach(System.out::print);

        System.out.println();

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::print);

        System.out.println();

        List<String> uniqueCharacters = Arrays.stream(new String[]{"goodbye", "world"})
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(toList());

        uniqueCharacters.forEach(System.out::print);
    }
}
