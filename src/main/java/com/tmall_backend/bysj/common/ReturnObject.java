package com.tmall_backend.bysj.common;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Data
public class ReturnObject {
    Boolean success;
    Object result;
    String message;
    Integer code;

    public ReturnObject() {

    }

    public ReturnObject(Boolean success, Object result, Integer code) {
        this.success = success;
        this.result = result;
        this.message = "";
        this.code = code;
    }

    public ReturnObject(Boolean success, Object result, String message, Integer code) {
        this.success = success;
        this.result = result;
        this.message = message;
        this.code = code;
    }
}
