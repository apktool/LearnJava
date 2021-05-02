package com.apktool.mybatis.dao;

import java.util.List;

import com.apktool.mybatis.entity.UserEntity;
import com.apktool.mybatis.entity.UserQueryModel;

public interface UserMapper {
    int insert(UserEntity userEntity);

    List<UserEntity> list(UserQueryModel model);

    UserEntity findById(Long id);

    List<UserEntity> findByName(String name);
}