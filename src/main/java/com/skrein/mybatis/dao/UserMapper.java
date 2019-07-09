package com.skrein.mybatis.dao;

import com.skrein.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: hujiansong
 * @Date: 2019/6/8 15:38
 * @since: 1.8
 */
@Mapper
public interface UserMapper {

    User selectOne(String id);

    List<String> selectNames();
}
