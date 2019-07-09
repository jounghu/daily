package com.skrein.spring;

/**
 * @author :hujiansong
 * @date :2019/6/18 10:53
 * @since :1.8
 */
public class UserServiceFactory {


    public UserService getUserService() {
        return new UserService("fac-hjs", "fac-123");
    }

}
