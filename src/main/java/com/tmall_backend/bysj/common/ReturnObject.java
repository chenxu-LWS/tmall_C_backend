package com.tmall_backend.bysj.common;

import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Data
public class ReturnObject {
    Boolean success;
    Object result;
    Integer code;
    String message;

    public ReturnObject() {

    }

    public ReturnObject(Boolean success, Object result, Integer code) {
        this.success = success;
        this.result = result;
        this.code = code;
    }

    public ReturnObject(Boolean success, Object result, Integer code, String message) {
        this.success = success;
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public ReturnObject(ErrInfo errInfo) {
        this.success = false;
        this.result = null;
        this.code = errInfo.getCode();
        this.message = errInfo.getMessage();
    }

    public ReturnObject(BizException e) {
        this.success = false;
        this.result = null;
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}
