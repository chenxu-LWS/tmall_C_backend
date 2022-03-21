package com.tmall_backend.bysj.controller.behavior.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnsureOrderDTO {
    private Integer orderInfoId;
    private Integer commodityId;
}
