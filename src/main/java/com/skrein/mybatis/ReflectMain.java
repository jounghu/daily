package com.skrein.mybatis;

import com.skrein.mybatis.model.User;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

/**
 * @Author: hujiansong
 * @Date: 2019/6/11 14:23
 * @since: 1.8
 */
public class ReflectMain {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        MetaObject metaObject = MetaObject.forObject(user, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        Object id = metaObject.getValue("id");
        System.out.println(id);
    }
}
