package com.apktool.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryModel extends BaseQueryModel {
    private String name;

    private int age;
}