package com.skrein.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author :hujiansong
 * @date :2019/6/19 14:07
 * @since :1.8
 */
@Repository
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new UserDao();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
