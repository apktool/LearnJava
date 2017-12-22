package com.service;

import com.dao.UserDaoOracleImpl;
import org.junit.Test;

public class UserServiceImplTest {

    @Test
    public void BasicFactory() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.setUserDao(new UserDaoOracleImpl());
        userServiceImpl.getUser();
    }
}
