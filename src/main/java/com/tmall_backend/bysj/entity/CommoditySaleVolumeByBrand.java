package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommoditySaleVolumeByBrand {
    private Integer brandId; // 品牌ID
    private Double totalPrice; // 当前品牌ID的销量
}
