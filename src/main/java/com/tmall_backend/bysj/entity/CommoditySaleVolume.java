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
public class CommoditySaleVolume {
    private Integer categoryId; // 种类ID
    private Double totalPrice; // 当前种类ID的销量
}
