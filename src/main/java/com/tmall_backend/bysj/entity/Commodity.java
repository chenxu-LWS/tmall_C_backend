package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {
    private Integer id;
    private String name;
    private Integer categoryID;// 所属品类ID
    private Integer brandID;// 所属品牌ID
    private Double price;// 商品售价
    private String props;// 商品其他属性，json字符串
    private String detail;
    private Integer status;// 商品状态,已创建为(0)，已上架为1，已下架为2
    private Integer inventory;
}
