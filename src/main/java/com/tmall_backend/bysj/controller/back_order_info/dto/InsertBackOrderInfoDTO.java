package com.tmall_backend.bysj.controller.back_order_info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-12
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsertBackOrderInfoDTO {
    private Integer orderInfoId;// 对应订单ID
    private Integer commodityId;// 商品ID
    private Integer backNum;// 退回的数量
    private Double price;// 退回的总价格
}
