package com.tmall_backend.bysj.service.behavior.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealPriceFromOrderSingleComDTO {
    private Integer commodityId;
    private Double oldPrice;
    private Double actPrice;
    private Integer number;
}
