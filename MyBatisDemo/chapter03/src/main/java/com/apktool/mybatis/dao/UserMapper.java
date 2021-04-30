package com.apktool.mybatis.dao;

import java.util.List;

import com.apktool.mybatis.entity.UserEntity;

public interface UserMapper {
    int insert(UserEntity userEntity);

    List<UserEntity> list(UserEntity userEntity);

    UserEntity findById(Long id);
}