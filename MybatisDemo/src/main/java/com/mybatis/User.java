package com.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String dept;
    private String phone;
    private String website;

    public User(String name, String dept, String phone, String website) {
        this.name = name;
        this.dept = dept;
        this.phone = phone;
        this.website = website;
    }
}
