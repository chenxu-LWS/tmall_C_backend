package com.tmall_backend.bysj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-10
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private Integer id;
    private String customerName;// 订单用户名
    private String detail;// 订单详情，k-v json array形式
    private Double orderPrice;// 订单总价
    private Date orderTime;// 订单发生时间
}
