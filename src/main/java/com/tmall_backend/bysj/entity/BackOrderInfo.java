package com.tmall_backend.bysj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BackOrderInfo {
    private Integer id;
    private Integer orderInfoId;// 对应订单ID
    private Integer commodityId;// 商品ID
    private Integer backNum;// 退回的数量
    private Double price;// 退回的总价格
    private Date backOrderTime;// 退单时间
}
