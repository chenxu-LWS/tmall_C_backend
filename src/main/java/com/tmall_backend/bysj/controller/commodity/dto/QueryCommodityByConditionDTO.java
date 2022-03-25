package com.tmall_backend.bysj.controller.commodity.dto;

import com.tmall_backend.bysj.controller.PageBaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCommodityByConditionDTO extends PageBaseDTO {
    // 搜索
    private String ambiName;
    // 筛选条件
    private Integer categoryId;
    private Integer brandId;
    private String propK;
    private String propV;
    private Double priceLow;
    private Double priceHigh;
    // 排序方式
    private String sortedBy;
    private Boolean sortDesc;// 从小到大为0，从大到小则为1
    private Boolean onlyOnSale;// 只显示上架商品
}
