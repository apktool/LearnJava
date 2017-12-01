package com.notation;

import org.springframework.stereotype.Repository;

@Repository(value = "customDao1")
public class CustomerDaoMySQLImpl implements CustomerDaoInterface{
    public void save(){
        System.out.println("CustomDao's Implementation : MySQL Dao");
    }
}
