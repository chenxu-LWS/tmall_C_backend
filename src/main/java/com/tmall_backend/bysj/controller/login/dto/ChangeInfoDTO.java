package com.tmall_backend.bysj.controller.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeInfoDTO {
    private Integer age;
    private Integer sex; // 0表示女，1表示男
}
