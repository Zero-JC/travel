package com.zero.travel.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录的传输对象
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 19:04
 */
@Data
public class SysUserDTO implements Serializable {

    private String username;

    private String password;

    private Integer code;

}
