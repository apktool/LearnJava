package com.chapter8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// Factory Pattern

public class FactoryMain {
    private static class ProductFactory {
        public static Product createProduct(String name) {
            switch (name) {
                case "loan":
                    return new Loan();
                case "stock":
                    return new Stock();
                case "bond":
                    return new Bond();
                default:
                    throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambda(String name) {
            Map<String, Supplier<Product>> map = new HashMap<>();
            map.put("loan", Loan::new);
            map.put("stock", Stock::new);
            map.put("bond", Bond::new);

            Supplier<Product> p = map.get(name);
            if (p != null) {
                return p.get();
            }
            throw new RuntimeException("No such product " + name);
        }
    }

    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");
        p1.display();

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();
        p2.display();

        Product p3 = ProductFactory.createProductLambda("loan");
        p3.display();
    }

    private interface Product {
        void display();
    }

    private static class Loan implements Product {
        @Override
        public void display() {
            System.out.println("Loan");
        }
    }

    private static class Stock implements Product {
        @Override
        public void display() {
            System.out.println("Stock");
        }
    }

    private static class Bond implements Product {
        @Override
        public void display() {
            System.out.println("Bond");
        }
    }


}
