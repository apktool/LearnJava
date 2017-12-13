package com.springmvc;


public class User {
    private String username;
    private String password;
    private String address;
    private Boolean receivePaper;
    private String[] favoriteFrameworks;
    private String gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getReceivePaper() {
        return receivePaper;
    }

    public void setReceivePaper(Boolean receivePaper) {
        this.receivePaper = receivePaper;
    }

    public String[] getFavoriteFrameworks() {
        return favoriteFrameworks;
    }

    public void setFavoriteFrameworks(String[] favoriteFrameworks) {
        this.favoriteFrameworks = favoriteFrameworks;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
