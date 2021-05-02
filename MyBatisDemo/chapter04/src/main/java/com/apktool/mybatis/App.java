package com.apktool.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.apktool.mybatis.dao.UserMapper;
import com.apktool.mybatis.entity.UserEntity;
import com.apktool.mybatis.entity.UserQueryModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public void run() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        UserEntity user1 = new UserEntity();
        user1.setName("li");
        user1.setAge(20);
        int res1 = mapper.insert(user1);
        sqlSession.commit();
        log.info("res is " + res1);

        UserQueryModel user2 = new UserQueryModel();
        user2.setName("li");
        user2.setAge(20);
        user2.setPageNo(0);
        user2.setPageSize(10);
        List<UserEntity> res2 = mapper.list(user2);
        res2.forEach(t -> log.info("{} {} {}", t.getId(), t.getName(), t.getAge()));

        UserEntity user3 = mapper.findById(1L);
        log.info("{} {} {}", user3.getId(), user3.getName(), user3.getAge());

        List<UserEntity> user4 = mapper.findByName("li");
        user4.forEach(t -> log.info("{} {} {}", t.getId(), t.getName(), t.getAge()));

        sqlSession.close();
    }

    public static void main(String[] args) throws IOException {
        new App().run();
    }
}