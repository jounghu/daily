package com.skrein.mybatis;

import com.skrein.mybatis.dao.UserMapper;
import com.skrein.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @Author: hujiansong
 * @Date: 2019/6/8 15:38
 * @since: 1.8
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-conf.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        System.out.println(mapper.selectOne("1"));
//        System.out.println(mapper.selectNames());

    }
}
