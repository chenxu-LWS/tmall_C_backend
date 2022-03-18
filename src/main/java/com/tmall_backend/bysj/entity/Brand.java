package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private Integer id;
    private String name; // 品牌名
}
