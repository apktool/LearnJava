package com.chapter10;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OptionalMain {
    public static void main(String[] args) {
        Optional<Person> opt = Optional.of(new Person(Optional.of(new Car(Optional.of(new Insurance("BMW"))))));
        System.out.println(getCarInsuranceName(opt));

        List<Person> list = Arrays.asList(
                new Person(Optional.of(new Car(Optional.of(new Insurance("BMW"))))),
                new Person(Optional.of(new Car(Optional.of(new Insurance("BENZ"))))),
                new Person(Optional.of(new Car(Optional.of(new Insurance("MASERATI")))))
        );

        System.out.println(getCarInsuranceNames(list));
    }


    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
