package com.notation;

import org.springframework.stereotype.Component;

@Component(value = "customDao")
public class CustomerDaoImpl implements CustomerDaoInterface{
    @Override
    public void save() {
        System.out.println("IOC Notation");
    }
}
