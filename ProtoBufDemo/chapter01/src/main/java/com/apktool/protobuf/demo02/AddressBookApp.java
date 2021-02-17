package com.apktool.protobuf.demo02;

import com.apktool.protobuf.AddressBookProtos;
import com.apktool.protobuf.PersonProtos;

/**
 * 测试 protobuf import 功能
 */
public class AddressBookApp {
    public AddressBookProtos.AddressBook run() {
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

        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder()
                .addPeople(person)
                .build();

        return addressBook;
    }

    public static void main(String[] args) {
        AddressBookApp app = new AddressBookApp();
        AddressBookProtos.AddressBook addressBook = app.run();
        System.out.println(addressBook);
    }
}
