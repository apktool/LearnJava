package com.dao;

import org.springframework.stereotype.Component;

@Component
public class UserDaoOracleImpl implements UserDao{
    public void getUser() {
        System.out.println("User Dao Oracle");
    }
}
