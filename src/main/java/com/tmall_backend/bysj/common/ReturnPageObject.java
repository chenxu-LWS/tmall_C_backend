package com.tmall_backend.bysj.common;

import java.util.List;

import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.page.PageBean;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-15
 */
@Data
public class ReturnPageObject<T> {
    Boolean success;
    List<T> result;
    Integer code;
    String message;
    Integer pageNo;
    Integer pageSize;
    Integer totalNum;

    public ReturnPageObject() {

    }

    public ReturnPageObject(Boolean success, PageBean<T> pageBean, Integer code) {
        this.success = success;
        this.result = pageBean.getList();
        this.totalNum = pageBean.getTotalNum();
        this.pageSize = pageBean.getPageSize();
        this.pageNo = pageBean.getPageNo();
        this.code = code;
        this.message = null;
    }

    public ReturnPageObject(ErrInfo errInfo) {
        this.success = false;
        this.result = null;
        this.code = errInfo.getCode();
        this.message = errInfo.getMessage();
        this.pageNo = -1;
        this.pageSize = -1;
        this.totalNum = -1;
    }

    public ReturnPageObject(BizException e) {
        this.success = false;
        this.result = null;
        this.code = e.getCode();
        this.message = e.getMessage();
        this.pageNo = -1;
        this.pageSize = -1;
        this.totalNum = -1;
    }
}
