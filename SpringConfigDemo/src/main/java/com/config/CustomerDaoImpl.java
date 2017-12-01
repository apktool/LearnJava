package com.config;

import org.springframework.stereotype.Repository;

@Repository(value = "customerDao")
public class CustomerDaoImpl implements CustomerDaoInterface{
    @Override
    public void save() {
        System.out.println("CustomerDaoImple -> save");
    }
}
