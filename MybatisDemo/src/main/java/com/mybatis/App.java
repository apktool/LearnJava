package com.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class App {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }


    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = session.selectOne("com.mybatis.UserMapper.findByKey", 1);
            if(user!=null){
                String userInfo = "Name："+user.getName()+", Department："+user.getDept()+", HomePage："+user.getWebsite();
                System.out.println(userInfo);
            }

            User user1 = new User("apktool", "Tech", "120", "http://www.google.com");
            session.insert("com.mybatis.UserMapper.insert", user1);
            session.commit();

            User user2 = new User();
            user2.setId(Long.valueOf(1));
            user2.setName("apktool");
            user2.setPhone("119");
            session.update("com.mybatis.UserMapper.update", user2);
            session.commit();

            User user3 = new User();
            user3.setId(Long.valueOf(1));
            session.update("com.mybatis.UserMapper.delete", user3);
            session.commit();

        } finally {
            session.close();
        }
    }
}
