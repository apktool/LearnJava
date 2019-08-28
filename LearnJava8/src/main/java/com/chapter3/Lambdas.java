package com.chapter3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambdas {
    public static void main(String[] args) {
        // Simple example
        Runnable r = () -> System.out.println("Hello!");
        r.run();

        // Filtering with lambdas
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples);


        // [Lambdas.Apple(weight=155, color=green), Lambdas.Apple(weight=120, color=red), Lambdas.Apple(weight=80, color=green)]
        inventory.sort((a1, a2) -> a2.getWeight() - a1.getWeight());
        System.out.println(inventory);
    }

    private interface ApplePredicate {
        public boolean test(Apple a);
    }

    private static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Data
    @AllArgsConstructor
    private static class Apple {
        private int weight;
        private String color;
    }

}
