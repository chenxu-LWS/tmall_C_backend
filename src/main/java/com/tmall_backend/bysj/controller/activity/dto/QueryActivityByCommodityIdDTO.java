package com.tmall_backend.bysj.controller.activity.dto;

import java.sql.Timestamp;

import com.tmall_backend.bysj.service.activity.dto.ActivityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryActivityByCommodityIdDTO {
    private Integer id; // 活动ID
    private Integer type; // 活动类型,1表示优惠券,2表示满减,3表示折扣
    private String activityName; // 活动名称
    private Timestamp startTime; // 活动开始时间
    private Timestamp endTime; // 活动结束时间
    private Integer INT01;
    private Integer INT02;

    public QueryActivityByCommodityIdDTO(ActivityDTO activityDTO) {
        this.id = activityDTO.getId();
        this.type = activityDTO.getType();
        this.activityName = activityDTO.getActivityName();
        this.startTime = activityDTO.getStartTime();
        this.endTime = activityDTO.getEndTime();
        this.INT01 = activityDTO.getINT01();
        this.INT02 = activityDTO.getINT02();
    }
}
