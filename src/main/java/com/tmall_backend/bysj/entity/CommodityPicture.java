package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Data
@AllArgsConstructor
public class CommodityPicture {
    private Integer id;
    private Integer commodityID;// 商品ID
    private Integer pictureType;// 图片类型(主图片0,详情页图片1)
    private Integer pictureSequence;// 图片顺序(1、2...)
    private String pictureObj;// OSS对象名
}
