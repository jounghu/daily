package com.skrein.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Author: hujiansong
 * @Date: 2019/6/8 15:38
 * @since: 1.8
 */
@Data
@Alias("user")
public class User {

    private Integer id;

    private String username;

    private String password;
}
