package com.tmall_backend.bysj.exception;

import com.tmall_backend.bysj.constants.ErrInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    protected Integer code;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(ErrInfo info) {
        super(info.getMessage());
        this.code = info.getCode();
    }
}
