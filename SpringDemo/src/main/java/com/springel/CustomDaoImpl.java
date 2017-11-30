package com.springel;

public class CustomDaoImpl implements CustomDaoInterface{
    private Customer customer;
    private String customerName;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "CustomerDaoImpl [customer=" + customer + ", customName=" + customerName + "]";
    }
}
