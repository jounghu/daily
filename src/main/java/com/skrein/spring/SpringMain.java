package com.skrein.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :hujiansong
 * @date :2019/6/17 10:23
 * @since :1.8
 */
public class SpringMain {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
//        System.out.println(context.getBean("myFactoryBean"));
//        System.out.println(context.getBean("&myFactoryBean"));
//        context.getBean("userDao");
//        user.sayHello();
    }
}
