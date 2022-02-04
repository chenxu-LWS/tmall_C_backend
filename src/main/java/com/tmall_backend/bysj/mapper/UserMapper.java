package com.tmall_backend.bysj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.User;

/**
 * @author LiuWenshuo
 * Created on 2022-01-30
 */
@Mapper
@Component
public interface UserMapper {
    public User findUserById(String id);
}
