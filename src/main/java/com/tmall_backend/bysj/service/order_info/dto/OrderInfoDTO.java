package com.tmall_backend.bysj.service.order_info.dto;

import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.tmall_backend.bysj.entity.OrderInfo;

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
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO {
    private Integer id;
    private String customerName;// 订单用户名
    private Map<String, Object> detail;// 订单详情，k-v json Map
    private Double orderPrice;// 订单总价
    private Date orderTime;// 订单发生时间

    public OrderInfoDTO(OrderInfo orderInfo) {
        this.id = orderInfo.getId();
        this.customerName = orderInfo.getCustomerName();
        this.orderPrice = orderInfo.getOrderPrice();
        this.orderTime = orderInfo.getOrderTime();
        this.detail = JSON.parseObject(orderInfo.getDetail());
    }
}
