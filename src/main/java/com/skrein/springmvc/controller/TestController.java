package com.skrein.springmvc.controller;

import com.skrein.spring.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author :hujiansong
 * @date :2019/6/21 16:46
 * @since :1.8
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("")
    @ResponseBody
    public String test() {
        userDao.sayHello();
        return "OK";
    }
}
