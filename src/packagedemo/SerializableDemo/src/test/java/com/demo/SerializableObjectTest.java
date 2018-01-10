package com.demo;

import org.junit.Test;

import java.io.*;

public class SerializableObjectTest{
    String fileName = "src/main/resources/output.txt";

    @Test
    public void writeSerializableObject() {
        try{
            Man man=new Man("apktool","123456");
            Person person=new Person(man,"person",21);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject("string");
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void readSerializableObject() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            String string = (String) objectInputStream.readObject();
            Person person = (Person) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(string + "| age: " + person.getAge() + ", man username: " + person.getMan().getUsername());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
