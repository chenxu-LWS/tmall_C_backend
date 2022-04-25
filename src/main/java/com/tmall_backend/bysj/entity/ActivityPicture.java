package com.tmall_backend.bysj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityPicture {
    private Integer activityID;// 商品ID
    private String pictureObj;// OSS对象名
}
