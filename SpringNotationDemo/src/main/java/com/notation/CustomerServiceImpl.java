package com.notation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerServiceInterface{
    @Autowired
    private CustomerDaoImpl customerDao;

    @Override
    public void save() {
        customerDao.save();
    }
}
