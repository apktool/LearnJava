package com.notation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerServiceInterface{
    @Autowired
    @Qualifier("customDao1")
    private CustomerDaoInterface customerDao;

    @Override
    public void save() {
        customerDao.save();
    }
}
