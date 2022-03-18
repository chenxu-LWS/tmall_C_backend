package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-05
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name; // 类型名
    private Integer parentCategoryID; // 父类型ID
    private Integer level; // 类型所属的层级
    private Integer commodityNum; // 当前种类下的商品数量
}
