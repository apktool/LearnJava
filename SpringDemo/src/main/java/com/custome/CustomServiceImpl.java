package com.custome;

public class CustomServiceImpl implements CustomServiceInterface{
    private CustomDaoImpl customDao;

    /*
    public CustomServiceImpl(CustomDaoImpl customDao){
        this.customDao = customDao;
    }
    */

    public void setCustomDao(CustomDaoImpl customDao){
        this.customDao = customDao;
    }

    @Override
    public void save() {
        customDao.save();
    }
}
