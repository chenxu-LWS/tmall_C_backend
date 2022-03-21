package com.tmall_backend.bysj.service.trolley.dto;

import java.util.List;

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
public class TrolleyDTO {
    Integer customerId;
    Integer commodityNum;// 当前购物车商品总数
    List<CommodityForTrolleyDTO> commodityDetail;// 当前购物车的所有商品的详情
}
