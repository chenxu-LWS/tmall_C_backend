package com.tmall_backend.bysj.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer id; // 活动ID
    private Integer type; // 活动类型,1表示优惠券,2表示满减,3表示折扣
    private String activityName; // 活动名称
    private String DSL; // 活动对应的DSL语言
    private Timestamp startTime; // 活动开始时间
    private Timestamp endTime; // 活动结束时间
    private Integer online; // 活动是否发布在线
}
