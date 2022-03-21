package com.tmall_backend.bysj.controller.order_info.dto;


import com.tmall_backend.bysj.controller.PageBaseDTO;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-15
 */
@Data
public class QueryOrderInfoByCustomerNameByPageDTO extends PageBaseDTO {
    private String customerName;
}
