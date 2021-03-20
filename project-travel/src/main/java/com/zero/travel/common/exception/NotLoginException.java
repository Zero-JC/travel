package com.zero.travel.common.exception;

/**
 * 用户未登录异常
 * @author LJC
 * @version 1.0
 * @date 2021/3/19 20:16
 */
public class NotLoginException extends Exception{

    private String message;

    public NotLoginException() {

    }

    public NotLoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
