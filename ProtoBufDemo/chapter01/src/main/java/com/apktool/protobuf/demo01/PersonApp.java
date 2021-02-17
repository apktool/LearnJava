package com.apktool.protobuf.demo01;

import com.apktool.protobuf.PersonProtos;

/**
 * 测试 protobuf 基本功能
 */
public class PersonApp {
    public PersonProtos.Person run() {
        PersonProtos.Person.PhoneNumber phoneNumber = PersonProtos.Person.PhoneNumber.newBuilder()
                .setType(PersonProtos.Person.PhoneType.HOME)
                .setNumber("0755-110")
                .build();

        PersonProtos.Person person = PersonProtos.Person.newBuilder()
                .setId(1)
                .setName("apktool")
                .setEmail("none@email.com")
                .addPhones(phoneNumber)
                .build();

        return person;
    }

    public static void main(String[] args) {
        PersonApp app = new PersonApp();
        PersonProtos.Person person = app.run();
        System.out.println(person);
    }
}