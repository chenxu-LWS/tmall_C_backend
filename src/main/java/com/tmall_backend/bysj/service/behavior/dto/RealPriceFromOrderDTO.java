package com.tmall_backend.bysj.service.behavior.dto;

import java.util.List;

import com.tmall_backend.bysj.service.activity.dto.ActivityDTO;

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
public class RealPriceFromOrderDTO {
    private List<RealPriceFromOrderSingleComDTO> commodityDetails;
    private Double totalRealPrice;
    private Double totalActPrice;
    private ActivityDTO activity;
}
