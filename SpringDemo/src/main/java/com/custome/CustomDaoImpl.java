package com.custome;

public class CustomDaoImpl implements CustomDaoInterface{
    @Override
    public void save() {
        System.out.println("CustomDaoImple is injected successfully.");
    }
}
