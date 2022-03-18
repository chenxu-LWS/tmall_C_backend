package com.tmall_backend.bysj.controller.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsLoginDTO {
    private String userName;
    private Boolean isLogin;
}
