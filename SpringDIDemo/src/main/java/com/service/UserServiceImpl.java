package com.service;

import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserDao{
    @Autowired
    @Qualifier("userDaoOracleImpl")
    UserDao userDao;

    public void getUser() {
        userDao.getUser();
    }
}
