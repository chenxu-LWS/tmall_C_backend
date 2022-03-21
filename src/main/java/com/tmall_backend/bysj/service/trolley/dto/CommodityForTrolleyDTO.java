package com.tmall_backend.bysj.service.trolley.dto;

import com.tmall_backend.bysj.entity.Brand;
import com.tmall_backend.bysj.entity.Category;
import com.tmall_backend.bysj.entity.Commodity;

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
public class CommodityForTrolleyDTO {
    private Integer id;
    private String name;
    private Category category;// 所属品类ID
    private Brand brand;// 所属品牌ID
    private Double price;// 商品售价
    private Integer status;// 商品状态,已创建为(0)，已上架为1，已下架为2
    private String detail;// 商品描述
    private Integer number;// 当前加购数量

    public CommodityForTrolleyDTO(Commodity commodity) {
        this.id = commodity.getId();
        this.name = commodity.getName();
        this.price = commodity.getPrice();
        this.status = commodity.getStatus();
        this.detail = commodity.getDetail();
    }
}
