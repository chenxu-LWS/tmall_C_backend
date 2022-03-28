package com.tmall_backend.bysj.service.activity.dto;

import java.sql.Timestamp;
import java.util.Objects;

import com.tmall_backend.bysj.entity.Activity;

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
public class ActivityDTO {
    private Integer id; // 活动ID
    private Integer type; // 活动类型,1表示优惠券,2表示满减,3表示折扣
    private String activityName; // 活动名称
    private String DSL; // 活动对应的DSL语言
    private Integer INT01;
    private Integer INT02;
    private Timestamp startTime; // 活动开始时间
    private Timestamp endTime; // 活动结束时间
    private Integer online; // 活动是否发布在线

    public ActivityDTO(Activity activity) {
        this.id = activity.getId();
        this.type = activity.getType();
        this.activityName = activity.getActivityName();
        this.DSL = activity.getDSL();
        this.startTime = activity.getStartTime();
        this.endTime = activity.getEndTime();
        this.online = activity.getOnline();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivityDTO dto = (ActivityDTO) o;
        return Objects.equals(id, dto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
