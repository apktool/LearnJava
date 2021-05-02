package com.apktool.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEntity extends BaseEntity{
    private String name;

    private int age;
}