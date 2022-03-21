package com.tmall_backend.bysj.controller.order_info.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Getter
@Setter
@ToString
public class InsertOrderInfoDTO {
    private String customerName;
    private String detail;
    private Double orderPrice;
}
