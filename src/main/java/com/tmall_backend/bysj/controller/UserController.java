package com.tmall_backend.bysj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.mapper.UserMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-02-01
 */
@Controller
@RequestMapping("/api/*/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/findUserById")
    @ResponseBody
    public Object findUserById(String userId) {
        return userMapper.findUserById(userId);
    }
}
