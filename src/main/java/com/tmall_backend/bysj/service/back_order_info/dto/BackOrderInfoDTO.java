package com.tmall_backend.bysj.service.back_order_info.dto;

import java.util.Date;

import com.tmall_backend.bysj.entity.BackOrderInfo;
import com.tmall_backend.bysj.service.commodity.dto.CommodityDTO;
import com.tmall_backend.bysj.service.order_info.dto.OrderInfoDTO;

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
public class BackOrderInfoDTO {
    private Integer id;
    private OrderInfoDTO orderInfoDTO;// 对应订单ID
    private CommodityDTO commodityDTO;// 商品ID
    private Integer backNum;// 退回的数量
    private Double price;// 退回的总价格
    private Date backOrderTime;// 退单时间

    public BackOrderInfoDTO(BackOrderInfo info) {
        this.id = info.getId();
        this.backNum = info.getBackNum();
        this.price = info.getPrice();
        this.backOrderTime = info.getBackOrderTime();
    }
}
