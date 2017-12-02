package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository(value = "customerDao")
public class CustomerDaoImpl implements CustomerDaoInterface{
    @Value("${jdbcUrl}")
    private String jdbcUrl;

    @Value("${driverClass}")
    private String driverClass;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Override
    public String toString(){
        return "CustomerDaoImpl [jdbcUrl=" + jdbcUrl + ", driverClass="
                + driverClass + ", user=" + user + ", password=" + password
                + "]";
    }

    @Override
    public void save() {
        System.out.println("CustomerDaoImple -> save");
    }
}
