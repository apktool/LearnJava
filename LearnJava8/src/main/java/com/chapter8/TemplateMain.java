package com.chapter8;


import java.util.function.Consumer;

// Template Pattern

public class TemplateMain {
    public static void main(String[] args) {
        processCustomer(1337, (Customer c) -> System.out.println("Hello!"));
    }

    public static void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    public static class Customer {
    }

    private static class Database {
        static Customer getCustomerWithId(int id) {
            System.out.print(id + " ");
            return new Customer();
        }
    }
}
