package com.tmall_backend.bysj.service.statistics.dto;


import com.tmall_backend.bysj.entity.Category;

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
public class CommoditySaleVolumeDTO {
    private Category category; // 种类ID
    private Double totalPrice; // 当前种类ID的销量
}
