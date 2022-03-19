package com.tmall_backend.bysj.controller;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-15
 */
@Data
public class PageBaseDTO {
    private Integer pageNo;
    private Integer pageSize;

    public Boolean hasNull() {
        return this.pageNo == null || this.pageSize == null;
    }
}
