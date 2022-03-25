package com.tmall_backend.bysj.controller.commodity.dto;

import com.tmall_backend.bysj.controller.PageBaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmbiQueryByNameByPageDTO extends PageBaseDTO {
    private String ambiName;
}
