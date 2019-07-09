package com.skrein.spring;

import org.springframework.stereotype.Repository;

/**
 * @author :hujiansong
 * @date :2019/6/18 14:14
 * @since :1.8
 */
@Repository(value = "userDao")
public class UserDao {

    private int num = 1;

    public void sayHello(){
        System.out.println("I am dao!!!");
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "num=" + num +
                '}';
    }
}
