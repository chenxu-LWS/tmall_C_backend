package com.tmall_backend.bysj.service.statistics.dto;

import com.tmall_backend.bysj.entity.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-05-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommoditySaleVolumeByBrandDTO {
    private Brand brand; // 品牌
    private Double totalPrice; // 当前品牌ID的销量
}
