package com.zero.travel.common.exception;

/**
 * 权限不足异常
 * @author LJC
 * @version 1.0
 * @date 2021/3/19 20:16
 */
public class NoPermissionException extends Exception{

    private String message;

    public NoPermissionException() {

    }

    public NoPermissionException(String message) {
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
