package com.zero.travel.common.enums;

import java.io.Serializable;

/**
 * 统一的响应状态码
 * @author LJC
 */
public enum StatusCode implements Serializable {

    //
    Success(200,"操作成功"),
    LoginSuccess(200,"登录成功"),
    Fail(500,"失败"),
    InvalidParams(500,"参数不合法"),
    UserNamePasswordNotBlank(500,"用户名或密码不能为空"),
    ParamNotBlank(500,"参数不能为空");

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
