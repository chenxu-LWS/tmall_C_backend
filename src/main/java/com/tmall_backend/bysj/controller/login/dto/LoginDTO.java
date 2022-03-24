package com.tmall_backend.bysj.controller.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String name;
    private String password;
}
