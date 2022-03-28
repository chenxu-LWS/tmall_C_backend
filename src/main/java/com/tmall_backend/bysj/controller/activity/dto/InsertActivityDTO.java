package com.tmall_backend.bysj.controller.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertActivityDTO {
    private String activityName;
    private String dsl;
}
