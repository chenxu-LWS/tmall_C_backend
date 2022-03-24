package com.tmall_backend.bysj.controller.login.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
@Setter
public class CustomerDTO {
    private String name;
    private String password;
    private Integer age;
    private Integer sex; // 0表示女，1表示男
    private String icon; // 用户头像BASE64
}
