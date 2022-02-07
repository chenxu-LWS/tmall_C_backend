package com.tmall_backend.bysj.common;

import java.util.List;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Data
public class ReturnListObject {
    Boolean success;
    List<Object> result;
    String info;

    public ReturnListObject() {
    }

    public ReturnListObject(Boolean success, List<Object> result) {
        this.success = success;
        this.result = result;
        this.info = "";
    }

    public ReturnListObject(Boolean success, List<Object> result, String info) {
        this.success = success;
        this.result = result;
        this.info = info;
    }
}
