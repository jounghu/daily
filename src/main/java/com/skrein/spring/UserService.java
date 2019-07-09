package com.skrein.spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author :hujiansong
 * @date :2019/6/17 10:23
 * @since :1.8
 */
@Data
@Service
public class UserService {

    private String username;

    private String password;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() {
        System.out.println("invoke post init......");
    }

    public UserService() {
    }

    public UserService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sayHello() {
        userDao.sayHello();
        System.out.println(this.username + "---" + this.password);
    }

}
