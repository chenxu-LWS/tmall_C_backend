package com.tmall_backend.bysj.controller.behavior.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommodityToTrolleyDTO {
    private Integer commodityId;
    private Integer number;
}
