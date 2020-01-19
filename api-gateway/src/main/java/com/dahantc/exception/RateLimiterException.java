package com.dahantc.exception;

/**
 * Created by Kevin Zhu on 2019/1/23 15:36 .
 */
public class RateLimiterException extends RuntimeException {

    private Integer code;

    public RateLimiterException() {
    }

    public RateLimiterException(Integer code , String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
