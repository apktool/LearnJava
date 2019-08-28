package com.chapter;

import java.util.ArrayList;
import java.util.stream.Stream;

public class SumDemo {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(4);

        System.out.println("-----------NUMS SUM-----------------");

        int sum1 = arrayList.stream().reduce((a, b) -> (a + b)).get();
        System.out.println(sum1);

        int sum2 = arrayList.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        System.out.println("------------MAX NUM------------------");

        int maxNum1 = arrayList.stream().reduce((a, b) -> a > b ? a : b).get();
        System.out.println(maxNum1);

        int maxNum2 = arrayList.stream().reduce(Integer::max).get();
        System.out.println(maxNum2);

        System.out.println("------------MIN NUM------------------");

        int minNum1 = arrayList.stream().reduce((a, b) -> a < b ? a : b).get();
        System.out.println(minNum1);

        int minNum2 = arrayList.stream().reduce(Integer::min).get();
        System.out.println(minNum2);
    }
}
