package com.dao;

import org.springframework.stereotype.Component;

@Component
public class UserDaoMysqlImpl implements UserDao{
    public void getUser() {
        System.out.println("User Dao Mysql");
    }
}
