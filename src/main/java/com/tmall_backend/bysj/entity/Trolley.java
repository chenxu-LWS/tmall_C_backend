package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trolley {
    Integer customerId;// 绑定的用户ID
    Integer commodityNum;// 当前购物车商品总数
    String commodityDetail;// 当前购物车的所有商品的详情，json
}
