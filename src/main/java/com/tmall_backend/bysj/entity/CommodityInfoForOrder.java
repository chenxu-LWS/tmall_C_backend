package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommodityInfoForOrder {
    private Integer number;
    private Integer status;
    private Double price;
}
