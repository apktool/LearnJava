package com.notation;

import org.springframework.stereotype.Repository;

@Repository(value = "customDao2")
public class CustomerDaoOracleImpl implements CustomerDaoInterface{
    @Override
    public void save() {
        System.out.println("CustomDao's Implementation : Oracle Dao");
    }
}
