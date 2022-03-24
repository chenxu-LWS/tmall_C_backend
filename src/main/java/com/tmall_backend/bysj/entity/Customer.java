package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-02-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Integer sex; // 0表示女，1表示男
    private String icon; // 用户头像BASE64
}
