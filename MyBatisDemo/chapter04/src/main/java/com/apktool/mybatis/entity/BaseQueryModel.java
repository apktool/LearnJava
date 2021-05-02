package com.apktool.mybatis.entity;

import com.apktool.mybatis.page.Pageable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseQueryModel implements Pageable {
    private int id;

    private int pageNo;

    private int pageSize;
}