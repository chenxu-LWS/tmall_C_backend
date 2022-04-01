package com.tmall_backend.bysj.controller.activity.dto;

import com.tmall_backend.bysj.controller.PageBaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryAvailableComsByActIdDTO extends PageBaseDTO {
    private Integer actId;
}
