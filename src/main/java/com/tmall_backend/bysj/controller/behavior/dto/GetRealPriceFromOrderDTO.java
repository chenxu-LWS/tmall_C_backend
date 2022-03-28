package com.tmall_backend.bysj.controller.behavior.dto;

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
public class GetRealPriceFromOrderDTO {
    private String detail;
    private Integer activityId;
}
